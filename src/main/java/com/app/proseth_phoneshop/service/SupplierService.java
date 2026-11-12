package com.app.proseth_phoneshop.service;

import com.app.proseth_phoneshop.entity.Supplier;

public interface SupplierService {
    Supplier create(Supplier supplier);
    Supplier getSupplierById(Long id);
}
