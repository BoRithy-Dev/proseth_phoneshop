package com.app.proseth_phoneshop.controller;
import com.app.proseth_phoneshop.dto.BrandDTO;
import com.app.proseth_phoneshop.dto.ModelDTO;
import com.app.proseth_phoneshop.entity.Brand;
import com.app.proseth_phoneshop.entity.Model;
import com.app.proseth_phoneshop.mapper.ModelMapstrucMapper;
import com.app.proseth_phoneshop.repository.ModelRepository;
import com.app.proseth_phoneshop.service.BrandService;
import com.app.proseth_phoneshop.service.ModelService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/brands")
public class BrandController {
  private final BrandService brandService;
  private final ModelService modelService;
  private final ModelMapstrucMapper modelMapstrucMapper;
  @PostMapping
  public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(brandService.create(brandDTO));
  }
//  @GetMapping
//  public ResponseEntity<List<BrandDTO>> getAll(){
//    return ResponseEntity.ok(brandService.getAllBrands());
//  }
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
  @GetMapping("/{id}/models")
  public ResponseEntity<?> getModelByBrandId(@PathVariable("id") Long id){
    return ResponseEntity.ok(modelService.getByBrand(id));
  }
//  @GetMapping
//  public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
//    Page<BrandDTO> page = brandService.getBrands(params);
//    return ResponseEntity.ok(page);
//  }

  @GetMapping
  public  ResponseEntity<?> getAllBrands(
          @RequestParam(value = "_page", defaultValue = "1") int page,
          @RequestParam(value = "_limit", defaultValue = "10") int limit
  ){
    Page<BrandDTO> result = brandService.getAllBrands(page, limit);
    return ResponseEntity.ok(result);
  }

}
