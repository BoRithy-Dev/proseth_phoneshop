package com.app.proseth_phoneshop.repository;

import com.app.proseth_phoneshop.entity.Brand;
import com.app.proseth_phoneshop.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>, JpaSpecificationExecutor<Model>{
    List<Model> findByName(String name);
}
