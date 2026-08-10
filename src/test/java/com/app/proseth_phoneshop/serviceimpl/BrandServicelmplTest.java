package com.app.proseth_phoneshop.serviceimpl;

import com.app.proseth_phoneshop.dto.BrandDTO;
import com.app.proseth_phoneshop.entity.Brand;
import com.app.proseth_phoneshop.mapper.BrandMapstrucMapper;
import com.app.proseth_phoneshop.repository.BrandRepository;
import com.app.proseth_phoneshop.service.BrandService;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class BrandServicelmplTest {
    @Mock
    private BrandRepository  brandRepository;
    @Mock
    private BrandService brandService;
    @Mock
    private BrandMapstrucMapper brandMapstrucMapper;
    @BeforeEach
    public void setUp(){
        brandService = new BrandServiceImpl(brandRepository,brandMapstrucMapper);
    }
    @Test
    public void crateTest(){
        //Give
        Long id = 1L;
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName("Apple");
        brandDTO.setId(id);
        Brand brand = new Brand();
        brand.setName("Apple");
        brand.setId(id);

        //When
        when(brandMapstrucMapper.toBrandEntity(any(BrandDTO.class))).thenReturn(brand);
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
        when(brandMapstrucMapper.toBrandDTO(any(Brand.class))).thenReturn(brandDTO);
        BrandDTO brandDTO1 = brandService.create(brandDTO);
        //Then
        assertEquals("Apple",brandDTO1.getName());
        assertEquals(1L,brandDTO1.getId());
    }
    public void getBrandByIdTest(){
        //Given
        Long id = 1L;
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(id);
        brandDTO.setName("Apple");
        Brand brand = new Brand();
        brand.setId(id);
        brand.setName("Apple");
        //when
        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));
        BrandDTO brandById = brandService.getBrandById(id);
        //Then
        assertEquals(1,brandById.getId());
    }
    public void updateBrandTest(){
        //Given
        Long id = 1L;
        Brand brand = new Brand();
        brand.setId(id);
        brand.setName("Old Apple");

        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(id);
        brandDTO.setName("New Apple");

        Brand brandUpdate = new Brand();
        brandUpdate.setId(id);
        brandUpdate.setName("New Apple");

        BrandDTO brandUpdateDTO = new BrandDTO();
        brandUpdateDTO.setId(id);
        brandUpdateDTO.setName("New Apple");

        //When
        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));
        when(brandRepository.save(any(Brand.class))).thenReturn(brandUpdate);
        when(brandMapstrucMapper.toBrandDTO(brandUpdate)).thenReturn(brandUpdateDTO);
        BrandDTO updateBrand = brandService.updateBrand(id, brandDTO);
        //Then
        assertEquals(1,updateBrand.getId());
        assertEquals("New Apple",updateBrand.getName());
    }

}


