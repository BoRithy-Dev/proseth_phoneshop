package com.app.proseth_phoneshop.controller;

import com.app.proseth_phoneshop.dto.ProductDTO;
import com.app.proseth_phoneshop.dto.ProductImportDTO;
import com.app.proseth_phoneshop.service.ProductServiceImportHistory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/productsimports")
public class ProductImportHistoryController {
    private final ProductServiceImportHistory productServiceImportHistory;
   @PostMapping
    public  ResponseEntity<?> create(@RequestBody ProductImportDTO productImportDTO){
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(productServiceImportHistory.create(productImportDTO));
   }
}
