package com.app.proseth_phoneshop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO {
    @NotEmpty
    private List<ProductSoldDTO> products;
    private LocalDate saleDate;
    @com.fasterxml.jackson.annotation.JsonProperty("saledate")
    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
}
