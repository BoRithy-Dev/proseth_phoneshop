package com.app.proseth_phoneshop.controller;
import com.app.proseth_phoneshop.dto.BrandDTO;
import com.app.proseth_phoneshop.entity.Brand;
import com.app.proseth_phoneshop.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/brands")
public class BrandController {
  private final BrandService brandService;
  @PostMapping
  public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(brandService.create(brandDTO));
  }
  @GetMapping
  public ResponseEntity<List<BrandDTO>> getAll(){
    return ResponseEntity.ok(brandService.getAllBrands());
  }
  @GetMapping("/name/{name}")
  public  ResponseEntity<List<BrandDTO>> getByName(@PathVariable String name){
    return ResponseEntity.ok(brandService.getByName(name));
  }
  @GetMapping("/{id}")
  public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long id){
    return ResponseEntity.ok(brandService.getBrandById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO){
    return ResponseEntity.ok(brandService.updateBrand(id, brandDTO));
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBrand(@PathVariable Long id){
    brandService.deletedBrand(id);
    return  ResponseEntity.noContent().build();
  }
}
