package com.app.proseth_phoneshop.serviceimpl;

import com.app.proseth_phoneshop.dto.BrandDTO;
import com.app.proseth_phoneshop.entity.Brand;
import com.app.proseth_phoneshop.mapper.BrandMapstrucMapper;
import com.app.proseth_phoneshop.repository.BrandRepository;
import com.app.proseth_phoneshop.service.BrandService;
import com.app.proseth_phoneshop.spec.BrandFilter;
import com.app.proseth_phoneshop.spec.BrandSpec;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapstrucMapper brandMapstrucMapper;

    @Override
    public BrandDTO create(BrandDTO brandDTO) {
        Brand entity = brandMapstrucMapper.toBrandEntity(brandDTO);
        Brand brandEntity = brandRepository.save(entity);
        return brandMapstrucMapper.toBrandDTO(brandEntity);
    }
//    @Override
//    public List<BrandDTO> getAllBrands() {
//        List<Brand> brandsEntity = brandRepository.findAll();
//        return brandsEntity.stream()
//                .map(brandMapstrucMapper::toBrandDTO).toList();
//    }

    @Override
    public BrandDTO getBrandById(Long id) {
        Brand brandEntity = brandRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Brand not found with id: " + id));
        return brandMapstrucMapper.toBrandDTO(brandEntity);
    }

    @Override
    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        Brand brandEntity = brandRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand Not Fund with id:" + id));
        brandEntity.setName(brandDTO.getName());
        Brand update = brandRepository.save(brandEntity);
        return brandMapstrucMapper.toBrandDTO(update);
    }

    @Override
    public void deletedBrand(Long id) {
        if (!brandRepository.existsById(id)){
            throw new RuntimeException("Brand Not Fund With Id" + id);
        }
        brandRepository.deleteById(id);
    }

    @Override
    public List<BrandDTO> getByName(String name) {
        List<Brand> brandsList = brandRepository.findByName(name);
        return brandsList.stream()
                .map(brandMapstrucMapper::toBrandDTO).toList();
    }

    @Override
    public List<BrandDTO> getBrands(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();
        if(params.containsKey("name")){
            String name = params.get("name");
            brandFilter.setName(name);
        }
        if(params.containsKey("id")){
            String id = params.get("id");
            brandFilter.setId(Long.parseLong(id));
        }
        BrandSpec brandSpec = new BrandSpec(brandFilter);
        return brandRepository.findAll(brandSpec).stream()
                .map(brandMapstrucMapper::toBrandDTO).toList();

    }



}
