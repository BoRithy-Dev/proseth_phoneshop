package com.app.proseth_phoneshop.controller;


import com.app.proseth_phoneshop.dto.ColorDTO;
import com.app.proseth_phoneshop.entity.Supplier;
import com.app.proseth_phoneshop.service.ColorService;
import com.app.proseth_phoneshop.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/supplier")

public class SupplierController {
    private  final SupplierService supplierService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Supplier supplier){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(supplierService.create(supplier));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getColorByID(@PathVariable Long id){
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }
}
