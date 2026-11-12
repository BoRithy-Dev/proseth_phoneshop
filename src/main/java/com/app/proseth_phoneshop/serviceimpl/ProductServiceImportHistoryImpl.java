package com.app.proseth_phoneshop.serviceimpl;

import com.app.proseth_phoneshop.dto.ProductDTO;
import com.app.proseth_phoneshop.dto.ProductImportDTO;
import com.app.proseth_phoneshop.entity.Product;
import com.app.proseth_phoneshop.entity.ProductImportHistory;
import com.app.proseth_phoneshop.entity.Supplier;
import com.app.proseth_phoneshop.exception.ApiExceptions;
import com.app.proseth_phoneshop.exception.ResourceNotFoundExceptions;
import com.app.proseth_phoneshop.mapper.ProductImportHistoryMapstructMapper;
import com.app.proseth_phoneshop.repository.ProductImportHistoryRepository;
import com.app.proseth_phoneshop.repository.ProductRepository;
import com.app.proseth_phoneshop.repository.SupplierRepository;
import com.app.proseth_phoneshop.service.ProductService;
import com.app.proseth_phoneshop.service.ProductServiceImportHistory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
@Transactional
@AllArgsConstructor
@Service
public class ProductServiceImportHistoryImpl implements ProductServiceImportHistory {
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductImportHistoryMapstructMapper productImportHistoryMapstructMapper;
    private  final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    @Override
    public ProductImportDTO create(ProductImportDTO productImportDTO) {
        if (productImportDTO.getImportUnit() <= 0) {
            throw new ApiExceptions(HttpStatus.BAD_REQUEST, "Import unit must be greater than zero");
        }
        if(productImportDTO.getPricePerUnit() == null || productImportDTO.getPricePerUnit().compareTo(BigDecimal.ZERO) <= 0){
            throw new ApiExceptions(HttpStatus.BAD_REQUEST,"Price per unit must be greater than zero");
        }
        ProductImportHistory productImportHistoryEntity = productImportHistoryMapstructMapper.toProductImportHistoryEntity(productImportDTO);
        if(productImportDTO.getSupplierId() !=null && productImportDTO.getSupplierId() > 0){
            Supplier supplier= supplierRepository.findById(productImportDTO.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundExceptions("Supplier:" ,productImportDTO.getSupplierId()));
            productImportHistoryEntity.setSupplier(supplier);
        }

                Product product = productRepository.findById(productImportDTO.getProductId())
                .orElseThrow(()-> new ResourceNotFoundExceptions("Product:" ,productImportDTO.getProductId()));
        productImportHistoryEntity.setProduct(product);
        BigDecimal totalCost = productImportDTO.getPricePerUnit().multiply(BigDecimal.valueOf(productImportDTO.getImportUnit()));
        productImportHistoryEntity.setTotalCost(totalCost);
        product.setStock(product.getStock() + productImportDTO.getImportUnit());
        productRepository.save(product);
        ProductImportHistory productImportHistory = productImportHistoryRepository.save(productImportHistoryEntity);
        return productImportHistoryMapstructMapper.toProductImportHistoryDTO(productImportHistory);
    }
}
