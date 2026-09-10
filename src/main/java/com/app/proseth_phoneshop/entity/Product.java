package com.app.proseth_phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.engine.profile.Fetch;

import java.math.BigDecimal;


@Entity
@Table(name = "products",
uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id","color_id"})})
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name",unique = true)
    private String name;

    @Column(name = "image_path")
    private String imagePath;


    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;


    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "sku")
    private String sku;


    @Column(name = "storage")
    private String storage;

    @Column(name = "cost_price",precision = 12,scale = 2)
    private BigDecimal costPrice;
    @Column(name = "selling_price",precision = 12,scale = 2)
    private BigDecimal sellingPrice;
    @Column(name = "stock")
    private Integer stock =0;
}