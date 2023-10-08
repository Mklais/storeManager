package com.klaisapp.storemanager.service;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public static final String PRODUCT_ADDED = "Product added successfully!";
    public static final String PRODUCT_UPDATED = "Product updated successfully!";
    public static final String PRODUCT_DELETED = "Product deleted successfully!";
    public static final String PRODUCT_ADD_FORM = "Add new Product";
    public static final String PRODUCT_EDIT_FORM = "Edit Product (ID: ";
    public static final String WAREHOUSE_ADD_FORM = "Add new Warehouse";
    public static final String WAREHOUSE_ADDED = "Warehouse added successfully!";
    public static final String WAREHOUSE_EDIT_FORM = "Edit Warehouse (ID: ";
    public static final String WAREHOUSE_UPDATED = "Warehouse updated successfully!";
    public static final String WAREHOUSE_DELETED = "Warehouse deleted successfully!";
    public String getProductAdded() {
        return PRODUCT_ADDED;
    }
    public String getProductUpdated() {
        return PRODUCT_UPDATED;
    }
    public String getProductDeleted() {
        return PRODUCT_DELETED;
    }
    public String getProductAddForm() {
        return PRODUCT_ADD_FORM;
    }
    public String getProductEditForm(Long id) {
        return (PRODUCT_EDIT_FORM + id + ")");
    }
    public String getWarehouseAddForm() {
        return WAREHOUSE_ADD_FORM;
    }
    public String getWarehouseAdded() {
        return WAREHOUSE_ADDED;
    }
    public String getWarehouseEditForm(Long id) {
        return (WAREHOUSE_EDIT_FORM + id + ")");
    }
    public String getWarehouseUpdated() {
        return WAREHOUSE_UPDATED;
    }
    public String getWarehouseDeleted() {
        return WAREHOUSE_DELETED;
    }
}
