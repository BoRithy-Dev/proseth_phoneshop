package com.app.proseth_phoneshop.repository;

import com.app.proseth_phoneshop.entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory,Long> {

}
