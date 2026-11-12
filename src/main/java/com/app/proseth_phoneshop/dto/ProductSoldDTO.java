package com.app.proseth_phoneshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSoldDTO {
    private Long productId;
    private Integer numberOfUnit;
}
