package com.app.proseth_phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="color_id")
    private Long id;
    @Column(name="color_name")
    private String name;
}
