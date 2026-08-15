package com.app.proseth_phoneshop.serviceimpl;

import com.app.proseth_phoneshop.dto.ModelDTO;
import com.app.proseth_phoneshop.entity.Brand;
import com.app.proseth_phoneshop.entity.Model;
import com.app.proseth_phoneshop.exception.ResourceNotFoundExceptions;
import com.app.proseth_phoneshop.mapper.ModelMapstrucMapper;
import com.app.proseth_phoneshop.repository.BrandRepository;
import com.app.proseth_phoneshop.repository.ModelRepository;
import com.app.proseth_phoneshop.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {
   private  final ModelRepository modelRepository;
    private final ModelMapstrucMapper modelMapstrucMapper;
    private  final BrandRepository brandRepository;
    public ModelDTO create(ModelDTO modelDTO) {
        Model model = modelMapstrucMapper.toModelEntity(modelDTO);
//        Long brandId = modelDTO.getBrandId();
     if (modelDTO.getBrandId() != null && modelDTO.getBrandId() > 0) {
        Brand brand = brandRepository.findById(modelDTO.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundExceptions("Brand:",+ modelDTO.getBrandId()));
        model.setBrand(brand);
        }

        Model modelEntity = modelRepository.save(model);
        return modelMapstrucMapper.toModelDTO(modelEntity);
    }

    @Override
    public List<ModelDTO> getAllModel() {
        List<Model> modelEntity = modelRepository.findAll();
        return modelEntity.stream()
                .map(modelMapstrucMapper::toModelDTO).toList();
    }

    @Override
    public ModelDTO getModelById(Long id) {
        Model modelEntity = modelRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundExceptions("Model",id));
        return modelMapstrucMapper.toModelDTO(modelEntity);
    }
}
