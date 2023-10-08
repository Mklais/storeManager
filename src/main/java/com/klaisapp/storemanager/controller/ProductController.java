package com.klaisapp.storemanager.controller;

import com.klaisapp.storemanager.exception.ProductNotFoundException;
import com.klaisapp.storemanager.model.Product;
import com.klaisapp.storemanager.model.Warehouse;
import com.klaisapp.storemanager.service.MessageService;
import com.klaisapp.storemanager.service.ProductService;
import com.klaisapp.storemanager.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private MessageService messageService;
    @Autowired
    private WarehouseService warehouseService;
    private boolean update = false;

    @GetMapping("/products")
    public String productsPage(Model model) {
        List<Product> products = productService.listAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/addForm")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", messageService.getProductAddForm());

        List<String> warehouseOptions = getAllWarehouses();

        model.addAttribute("warehouseOptions", warehouseOptions);

        return "product_form";
    }

    @PostMapping("/products/save")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes) {
        product.setProductCode(product.getProductCode());
        productService.save(product);

        addOrUpdateProduct(redirectAttributes);

        return "redirect:/products";
    }

    private void addOrUpdateProduct(RedirectAttributes redirectAttributes) {
        if (isUpdate()) {
            redirectAttributes.addFlashAttribute("message", messageService.getProductUpdated());
            setUpdate(false);
        } else {
            redirectAttributes.addFlashAttribute("message", messageService.getProductAdded());
        }
    }
    private List<String> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.listAllWarehouses();
        List<String> warehouseNames = new ArrayList<>();

        for (Warehouse warehouse : warehouses) {
            warehouseNames.add(warehouse.getName());
        }

        return warehouseNames;
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Product existingProduct = productService.findProductById(id);

            if (existingProduct == null) {
                redirectAttributes.addFlashAttribute("message", "Product not found!");
                return "redirect:/products";
            }

            model.addAttribute("product", existingProduct);
            model.addAttribute("pageTitle", messageService.getProductEditForm(id));
            List<String> warehouseOptions = getAllWarehouses();

            model.addAttribute("warehouseOptions", warehouseOptions);
            setUpdate(true);

            return "product_form";
        } catch (ProductNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("message", messageService.getProductDeleted());

        } catch (ProductNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> getProductById (@PathVariable("id") Long id) {
        Product product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}
