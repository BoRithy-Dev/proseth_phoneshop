package com.app.proseth_phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="product_import_historys")
public class ProductImportHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="import_id")
    private Long id;
    @Column(name ="date_import")
    private LocalDateTime dateImport;
//    @Column(name="import_no")
//    private String importNo;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @Column(name="import_unit")
    private Integer importUnit;
    @Column(name="price_per_unit",precision = 12,scale = 2)
    private BigDecimal pricePerUnit;
    @Column(name="total_cost")
    private BigDecimal totalCost;
}
