package com.app.proseth_phoneshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class ProductImportDTO {
//    private String importNo;
//    @NotNull(message = "Product id can't be null")

    private Long productId;
    private Long supplierId;
    @NotNull(message = "Import unit is required")
    @Min(value = 1, message = "Import unit must be greater than zero")
    private Integer importUnit;
    @NotNull(message = "Price per unit is required")
    @DecimalMin(value = "0.01", message = "Price per unit must be greater than zero")
    private BigDecimal pricePerUnit;
    @NotNull(message = "Import date can't be null")

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateImport;

        public void setImportDate(LocalDateTime dateImport){
        this.dateImport =(dateImport != null) ? dateImport:LocalDateTime.now();
    }
    public LocalDateTime getImportDate(){
        return dateImport;
    }

}
