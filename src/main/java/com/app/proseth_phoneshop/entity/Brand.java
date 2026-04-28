package com.app.proseth_phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="brand_id")
    private Long id;
    @Column(name="brand_name")
    private String name;

}
