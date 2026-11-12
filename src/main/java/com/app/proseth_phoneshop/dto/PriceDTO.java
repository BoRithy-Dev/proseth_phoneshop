package com.app.proseth_phoneshop.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceDTO {
//    @NotNull(message ="Price is required")
//    @DecimalMin(value = "0,01", message = "Price must be greater then 0")
    private BigDecimal price;
}
