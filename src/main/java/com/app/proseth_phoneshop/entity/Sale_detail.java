package com.app.proseth_phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.core.SpringVersion;

@Entity
@Data
@Table(name="sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_id")
    private Long id;
    @Column(name="invoice_no")
    private String invoice_no;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    @Column(name="total_amount")
    private Double total_amount;
    @Column(name="payment_method")
    private Double payment_method;
}
