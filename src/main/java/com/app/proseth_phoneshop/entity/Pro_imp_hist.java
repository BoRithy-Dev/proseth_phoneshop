package com.app.proseth_phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="suppliers")
public class supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="supplier_id")
    private Long id;
    @Column(name="supplier_name")
    private String name;
    @Column(name="supplier_phone")
    private String phone;
}
