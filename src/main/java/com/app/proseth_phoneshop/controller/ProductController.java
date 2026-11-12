package com.app.proseth_phoneshop.controller;

import com.app.proseth_phoneshop.dto.PriceDTO;
import com.app.proseth_phoneshop.dto.ProductDTO;
import com.app.proseth_phoneshop.entity.Product;
import com.app.proseth_phoneshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
   @PostMapping
    public  ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(productService.create(productDTO));
   }
   @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
       return ResponseEntity.ok(productService.getProductById(id));
   }
   @GetMapping
   public ResponseEntity<List<ProductDTO>> findAll(){
     return  ResponseEntity.ok(productService.getAllProduct());
   }
   @PostMapping("/{id}/setSellPrice")
    public ResponseEntity<?> setSellPrice(
            @PathVariable Long id,
            @RequestBody PriceDTO priceDTO
   ){
       productService.setSellPrice(id, priceDTO.getPrice());
       return ResponseEntity.ok().build();
   }
}

