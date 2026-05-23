package com.app.proseth_phoneshop.serviceimpl;

import com.app.proseth_phoneshop.dto.BrandDTO;
import com.app.proseth_phoneshop.entity.Brand;
import com.app.proseth_phoneshop.exception.ApiExceptions;
import com.app.proseth_phoneshop.exception.ResourceNotFoundExceptions;
import com.app.proseth_phoneshop.mapper.BrandMapstrucMapper;
import com.app.proseth_phoneshop.repository.BrandRepository;
import com.app.proseth_phoneshop.service.BrandService;
import com.app.proseth_phoneshop.util.PageUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
//
//    @Override
//    public List<BrandDTO> getAllBrands() {
//        List<Brand> brandsEntity = brandRepository.findAll();
//        return brandsEntity.stream()
//                .map(brandMapstrucMapper::toBrandDTO).toList();
//    }

    @Override
    public BrandDTO getBrandById(Long id) {
        Brand brandEntity = brandRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Brand",id));
        return brandMapstrucMapper.toBrandDTO(brandEntity);
    }
    @Override
    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
//        ApiExceptions ex = new ApiExceptions();
        Brand brandEntity = brandRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Brand",id));
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

//    @Override
//    public Page<BrandDTO> getBrands(Map<String, String> params) {
//        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
//        if(params.containsKey(PageUtil.PAGE_LIMIT)){
//            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
//        }
//
//        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
//        if(params.containsKey(PageUtil.PAGE_NUMBER)){
//            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
//        }
//        Pageable pageable = PageUtil.getPageable(pageNumber,pageLimit);
//        Page<Brand> brandPage = brandRepository.findAll(pageable);
//        return brandPage.map(brandMapstrucMapper::toBrandDTO);
//    }




    @Override
    public Page<BrandDTO> getAllBrands(int page, int limit) {
        Pageable pageable = PageUtil.getPageable(page,limit);
        Page<Brand> brandPage = brandRepository.findAll(pageable);
        return brandPage.map(brandMapstrucMapper::toBrandDTO);
    }
}
