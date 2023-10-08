package com.klaisapp.storemanager.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private Integer cost;
    private String unitOfMeasurement;
    private String category;
    private String origin;
    @Column(nullable = true)
    private String warehouse;
    @Column(nullable = true)
    private String productCode;

    public Product() {
    }

    public Product(Long id, String name, Integer cost, String unitOfMeasurement, String category, String origin, String warehouse, String productCode) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.unitOfMeasurement = unitOfMeasurement;
        this.category = category;
        this.origin = origin;
        this.warehouse = warehouse;
        this.productCode = productCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String imageUrl) {
        this.warehouse = imageUrl;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        if (productCode == null) {
            productCode = generateRandomProductCode();
        }

        this.productCode = productCode;
    }

    public String generateRandomProductCode() {
        String randomCode = "";
        for (int i = 0; i < 10; i++) {
            int randomChar = (int) (Math.random() * (58 + 1));
            if (randomChar < 10) {
                randomCode += randomChar;
            } else if (randomChar < 36) {
                randomCode += (char) (randomChar + 55);
            } else {
                randomCode += (char) (randomChar + 61);
            }
        }

        return randomCode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", UoM='" + unitOfMeasurement + '\'' +
                ", category='" + category + '\'' +
                ", origin='" + origin + '\'' +
                ", imageUrl='" + warehouse + '\'' +
                ", productCode='" + productCode + '\'' +
                '}';
    }
}
