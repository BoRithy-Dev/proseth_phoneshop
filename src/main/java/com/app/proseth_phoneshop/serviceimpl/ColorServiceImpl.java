package com.app.proseth_phoneshop.serviceimpl;

import com.app.proseth_phoneshop.dto.ColorDTO;
import com.app.proseth_phoneshop.entity.Color;
import com.app.proseth_phoneshop.exception.ResourceNotFoundExceptions;
import com.app.proseth_phoneshop.mapper.ColorMapstrucMapper;
import com.app.proseth_phoneshop.repository.ColorRepository;
import com.app.proseth_phoneshop.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorMapstrucMapper colorMapstrucMapper;
    private final ColorRepository colorRepository;
    @Override
    public ColorDTO create(ColorDTO colorDTO) {
        Color entity = colorMapstrucMapper.toColorEntity(colorDTO);
        Color colorEntity = colorRepository.save(entity);
        return colorMapstrucMapper.toColorDTO(colorEntity);
    }

    @Override
    public ColorDTO getById(Long id) {
        Color colorEntity = colorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Color",id));
        return colorMapstrucMapper.toColorDTO(colorEntity);
    }
}
