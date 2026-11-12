package com.app.proseth_phoneshop.repository;

import com.app.proseth_phoneshop.entity.Color;
import com.app.proseth_phoneshop.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{
//    List<Brand> findByName(String name);
}
