package com.app.proseth_phoneshop.service;

import com.app.proseth_phoneshop.dto.BrandDTO;

import java.util.List;
import java.util.Map;


public interface BrandService {

    BrandDTO create(BrandDTO brandDTO);
    BrandDTO getBrandById(Long id);
    BrandDTO updateBrand(Long id, BrandDTO brandDTO);
    void deletedBrand(Long id);
    List<BrandDTO> getByName(String name);
    List<BrandDTO> getBrands(Map<String, String> params);
}
