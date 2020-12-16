package com.example.ProductCatalog.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    private Long id;
    private String name;
    private String type;
    private double weight;
    private double cost;
}
