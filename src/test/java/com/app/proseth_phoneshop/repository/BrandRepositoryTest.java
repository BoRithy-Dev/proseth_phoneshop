package com.app.proseth_phoneshop.repository;

import com.app.proseth_phoneshop.entity.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BrandRepositoryTest {
    @Autowired
    BrandRepository brandRepository;
    @Test
    public void testFindByName(){
        //Given
//        Brand brand = new Brand();
//        brand.setName("Apple");

        Brand brand1 = new Brand();
        brand1.setName("Sum song");
        brandRepository.save(brand1);
        //When
        List<Brand> brands = brandRepository.findByName("Sum song");
        //Then
        assertEquals(1,brands.size());
        assertEquals("Sum song",brands.getFirst().getName());
        assertEquals(1,brands.getFirst().getId());

    }
}
