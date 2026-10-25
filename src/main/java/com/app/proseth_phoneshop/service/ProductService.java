package com.app.proseth_phoneshop.service;

import com.app.proseth_phoneshop.dto.ProductDTO;
import com.app.proseth_phoneshop.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
//    ProductDTO create(ProductDTO productDTO);
    Product create(ProductDTO productDTO);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProduct();
    void setSellPrice(Long id, BigDecimal price);
    void validateStock(Long productId, Integer numberOfUnit);
}
