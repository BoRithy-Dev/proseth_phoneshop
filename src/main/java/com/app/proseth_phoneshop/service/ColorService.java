package com.app.proseth_phoneshop.service;

import com.app.proseth_phoneshop.dto.ColorDTO;

public interface ColorService {
    ColorDTO create(ColorDTO colorDTO);
    ColorDTO getById(Long id);
}
