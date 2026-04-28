package com.app.proseth_phoneshop.service;

import com.app.proseth_phoneshop.dto.BrandDTO;
import com.app.proseth_phoneshop.entity.Brand;

import java.util.List;


public interface BrandService {

    BrandDTO create(BrandDTO brandDTO);
    List<BrandDTO> getAllBrands();
    BrandDTO getBrandById(Long id);
    BrandDTO updateBrand(Long id, BrandDTO brandDTO);
//    Brand delete(Long id);
    void deletedBrand(Long id);
    List<BrandDTO> getByName(String name);

}
