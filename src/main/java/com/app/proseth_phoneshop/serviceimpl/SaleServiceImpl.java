package com.app.proseth_phoneshop.serviceimpl;

import com.app.proseth_phoneshop.dto.ProductDTO;
import com.app.proseth_phoneshop.dto.ProductSoldDTO;
import com.app.proseth_phoneshop.dto.SaleDTO;
import com.app.proseth_phoneshop.entity.*;
import com.app.proseth_phoneshop.exception.ApiExceptions;
import com.app.proseth_phoneshop.exception.ProductAlreadyExistsException;
import com.app.proseth_phoneshop.exception.ResourceNotFoundExceptions;
import com.app.proseth_phoneshop.mapper.ProductMapstructMapper;
import com.app.proseth_phoneshop.repository.*;
import com.app.proseth_phoneshop.service.ProductService;
import com.app.proseth_phoneshop.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {
    private final ProductRepository productRepository;

    private final ProductService productService;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

//    public SaleServiceImpl(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    @Transactional
    @Override
    public void sale(SaleDTO saleDTO) {
        saveSale(saleDTO);
    }
    private void saveSale(SaleDTO saleDTO) {
        // 1. Validation ការពារ Null Pointer Exception
        if (saleDTO.getProducts() == null || saleDTO.getProducts().isEmpty()) {
            throw new ApiExceptions(HttpStatus.BAD_REQUEST, "Products list cannot be empty");
        }
        Sale sale = new Sale();
        sale.setSoleDate(saleDTO.getSaleDate());
        Sale saveSale = saleRepository.save(sale);

        List<Long> productIds = saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId)
                .toList();

        Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        //4. Save SaleDetails (Detail ) and Cut Stock
        List<SaleDetail> saleDetails =saleDTO.getProducts().stream()
                .map(ps ->{
                    Product product = productMap.get(ps.getProductId());
                    if (product == null){
                        throw new ApiExceptions(HttpStatus.BAD_REQUEST,"Product ID [%s] not found".formatted(ps.getProductId()));
                    }
                    if (product.getStock() < ps.getNumberOfUnit()) {
                        throw new ApiExceptions(HttpStatus.BAD_REQUEST, "Product [%s] out of stock".formatted(product.getName()));
                    }
                    BigDecimal amount = product.getSellingPrice().multiply(BigDecimal.valueOf(ps.getNumberOfUnit()));
                    product.setStock(product.getStock() - ps.getNumberOfUnit());
                    productRepository.save(product);
                    SaleDetail saleDetail = new SaleDetail();
                    saleDetail.setAmount(amount);
                    saleDetail.setSale(saveSale);
                    saleDetail.setProduct(product);
                    saleDetail.setUnit(ps.getNumberOfUnit());
                    return saleDetail;
                }).toList();
        saleDetailRepository.saveAll(saleDetails);

    }
}

