package com.app.proseth_phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "model")
@Data
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modelId")
    private Long id;

    @Column(name = "model_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;
}