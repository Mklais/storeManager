package com.klaisapp.storemanager.repository;

import com.klaisapp.storemanager.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    void deleteWarehouseById(Long id);
    Optional<Warehouse> findWarehouseById(Long id);
}

