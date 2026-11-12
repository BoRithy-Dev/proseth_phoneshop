package com.app.proseth_phoneshop.controller;


import com.app.proseth_phoneshop.dto.ColorDTO;
import com.app.proseth_phoneshop.entity.Color;
import com.app.proseth_phoneshop.mapper.ColorMapstrucMapper;
import com.app.proseth_phoneshop.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/colors")

public class ColorController {
    private  final ColorService colorService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ColorDTO colorDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(colorService.create(colorDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getColorByID(@PathVariable Long id){
        return ResponseEntity.ok(colorService.getById(id));
    }
}
