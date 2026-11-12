package com.app.proseth_phoneshop.service;

import com.app.proseth_phoneshop.dto.ProductDTO;
import com.app.proseth_phoneshop.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {
//    ProductDTO create(ProductDTO productDTO);
    Product create(ProductDTO productDTO);
    Product getByModeIdAndColorIdAndStorage(Long modelId, Long colorId, String storage);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProduct();
    void setSellPrice(Long id, BigDecimal price);
    void validateStock(Long productId, Integer numberOfUnit);
    Map<Integer,String> uploadProduct(MultipartFile file);
}
