package com.app.proseth_phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long id;
    @Column(name="customer_name")
    private String name;
    @Column(name="customer_phone")
    private String phone;
}
