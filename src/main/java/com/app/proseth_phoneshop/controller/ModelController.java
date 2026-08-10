package com.app.proseth_phoneshop.controller;


import com.app.proseth_phoneshop.dto.ModelDTO;
import com.app.proseth_phoneshop.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/models")
public class ModelController {
    private  final ModelService modelService;
@PostMapping
    public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO){
    return ResponseEntity.status(HttpStatus.CREATED).body(modelService.create(modelDTO));
}

@GetMapping
    public ResponseEntity<List<ModelDTO>> findAll(){
    return ResponseEntity.ok(modelService.getAllModel());
}

}
