package com.klaisapp.storemanager.service;

import com.klaisapp.storemanager.exception.ProductNotFoundException;
import com.klaisapp.storemanager.model.Product;
import com.klaisapp.storemanager.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> listAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
    public Product findProductById(Long id) {
        return productRepository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + "was not found"));
    }
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteProductById(id);
    }
    public void save(Product product) {
        productRepository.save(product);
    }
}
