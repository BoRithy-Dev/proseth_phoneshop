package com.app.proseth_phoneshop.dto;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDTO {
    private Long modelId;
    private Long colorId;
//    private String name;
//    private String sku;
    private String storage;
//    private BigDecimal costPrice;
    private BigDecimal sellingPrice;
//    private Integer stock;

}
