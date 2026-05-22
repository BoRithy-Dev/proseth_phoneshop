package com.app.proseth_phoneshop.service;

import com.app.proseth_phoneshop.dto.BrandDTO;
import com.app.proseth_phoneshop.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface BrandService {

    BrandDTO create(BrandDTO brandDTO);
//    List<BrandDTO> getAllBrands();
    BrandDTO getBrandById(Long id);
    BrandDTO updateBrand(Long id, BrandDTO brandDTO);
//    Brand delete(Long id);
    void deletedBrand(Long id);
    List<BrandDTO> getByName(String name);
    Page<BrandDTO> getAllBrands(int page, int size);
//    Page<BrandDTO> getBrands(Map<String, String> params);
}
