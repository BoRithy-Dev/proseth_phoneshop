package com.app.proseth_phoneshop.repository;

import com.app.proseth_phoneshop.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   boolean existsByModelIdAndColorIdAndStorage(
       Long modelId,
       Long colorId,
       String storage
    );
   Optional<Product> findByModelIdAndColorIdAndStorage(Long modelId, Long colorId,String storage);
}

