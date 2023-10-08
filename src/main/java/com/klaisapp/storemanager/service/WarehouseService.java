package com.klaisapp.storemanager.service;

import com.klaisapp.storemanager.exception.WarehouseNotFoundException;
import com.klaisapp.storemanager.model.Warehouse;
import com.klaisapp.storemanager.repository.WarehouseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> listAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Transactional
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteWarehouseById(id);
    }

    public void save(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    public Warehouse findWareHouseById(Long id) {
        return warehouseRepository.findWarehouseById(id).
                orElseThrow(() -> new WarehouseNotFoundException("Warehouse with id " + id + "was not found"));
    }
}

