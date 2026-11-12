package com.app.proseth_phoneshop.controller;

import com.app.proseth_phoneshop.dto.PriceDTO;
import com.app.proseth_phoneshop.dto.ProductDTO;
import com.app.proseth_phoneshop.dto.SaleDTO;
import com.app.proseth_phoneshop.service.ProductService;
import com.app.proseth_phoneshop.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sales")
public class SaleController {
    private final ProductService productService;
    private final SaleService saleService;
   @PostMapping
    public  ResponseEntity<?> create(@RequestBody SaleDTO saleDTO){
       saleService.sale(saleDTO);
       return ResponseEntity.ok().build();
   }

}

