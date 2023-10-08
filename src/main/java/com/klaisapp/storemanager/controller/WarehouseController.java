package com.klaisapp.storemanager.controller;

import com.klaisapp.storemanager.exception.WarehouseNotFoundException;
import com.klaisapp.storemanager.model.Warehouse;
import com.klaisapp.storemanager.service.MessageService;
import com.klaisapp.storemanager.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private MessageService messageService;
    private boolean update = false;

    @GetMapping("/warehouses")
    public String warehousePage(Model model) {
        List<Warehouse> warehouse = warehouseService.listAllWarehouses();
        model.addAttribute("warehouses", warehouse);
        return "warehouses";
    }
    @GetMapping("/addWarehouse")
    public String newWarehouseForm(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        model.addAttribute("pageTitle", messageService.getWarehouseAddForm());

        return "warehouse_form";
    }

    @PostMapping("/warehouses/save")
    public String saveWarehouse(Model model, Warehouse warehouse, RedirectAttributes redirectAttributes) {
        warehouseService.save(warehouse);

        addOrUpdateWarehouse(redirectAttributes);
        model.addAttribute("warehouses", warehouseService.listAllWarehouses());

        return "redirect:/warehouses";
    }
    private void addOrUpdateWarehouse(RedirectAttributes redirectAttributes) {
        if (isUpdate()) {
            redirectAttributes.addFlashAttribute("message", messageService.getWarehouseUpdated());
            setUpdate(false);
        } else {
            redirectAttributes.addFlashAttribute("message", messageService.getWarehouseAdded());
        }
    }

    @GetMapping("/warehouses/edit/{id}")
    public String editWarehouseForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Warehouse existingWarehouse = warehouseService.findWareHouseById(id);


            if (existingWarehouse == null) {
                redirectAttributes.addFlashAttribute("message", "Warehouse not found!");
                return "redirect:/warehouses";
            }

            model.addAttribute("warehouse", existingWarehouse);
            model.addAttribute("pageTitle", messageService.getWarehouseEditForm(id));
            setUpdate(true);

            return "warehouse_form";
        } catch (WarehouseNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/warehouses/delete/{id}")
    public String deleteWarehouse(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            warehouseService.deleteWarehouse(id);
            redirectAttributes.addFlashAttribute("message", messageService.getWarehouseDeleted());

        } catch (WarehouseNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/warehouses";
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}
