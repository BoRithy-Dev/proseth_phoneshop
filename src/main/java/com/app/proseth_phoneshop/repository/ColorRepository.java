package com.app.proseth_phoneshop.repository;

import com.app.proseth_phoneshop.entity.Brand;
import com.app.proseth_phoneshop.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long>{
//    List<Brand> findByName(String name);
}
