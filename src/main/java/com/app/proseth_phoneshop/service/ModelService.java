package com.app.proseth_phoneshop.service;

import com.app.proseth_phoneshop.dto.ModelDTO;

import java.util.List;

public interface ModelService {
    ModelDTO create(ModelDTO modelDTO);
    List<ModelDTO> getAllModel();
    ModelDTO getModelById(Long id);
}
