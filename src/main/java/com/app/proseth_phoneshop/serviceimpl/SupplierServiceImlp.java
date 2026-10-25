package com.app.proseth_phoneshop.serviceimpl;

import com.app.proseth_phoneshop.entity.Supplier;
import com.app.proseth_phoneshop.exception.ResourceNotFoundExceptions;
import com.app.proseth_phoneshop.repository.SupplierRepository;
import com.app.proseth_phoneshop.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SupplierServiceImlp  implements SupplierService {
     private final SupplierRepository supplierRepository;

    @Override
    public Supplier create(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Supplier" ,+ id));
    }
}
