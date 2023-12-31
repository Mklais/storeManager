package com.klaisapp.storemanager.repository;

import com.klaisapp.storemanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    void deleteProductById(Long id);
    Optional<Product> findProductById(Long id);
}
