package com.app.proseth_phoneshop.service;

import com.app.proseth_phoneshop.dto.ProductDTO;
import com.app.proseth_phoneshop.dto.SaleDTO;
import com.app.proseth_phoneshop.entity.Product;
import com.app.proseth_phoneshop.entity.Sale;

import java.math.BigDecimal;
import java.util.List;

public interface SaleService {
//    ProductDTO create(ProductDTO productDTO);
//    SaleDTO create(SaleDTO saleDTO);
//    ProductDTO getProductById(Long id);
//    List<ProductDTO> getAllProduct();
//    void setSellPrice(Long id, BigDecimal price);
    void sale (SaleDTO saleDTO);
}
