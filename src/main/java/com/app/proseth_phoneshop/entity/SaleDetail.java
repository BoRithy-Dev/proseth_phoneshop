package com.app.proseth_phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name="sale_details")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_detail_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sale_id",nullable = false)
    private Sale sale;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id",nullable = false)
    private Product product;
    @Column(name="sold amount")
    private BigDecimal amount;
    @Column(name="unit")
    private Integer unit;
//    public void setUnit(Integer sellingPrice) {
//}
}
