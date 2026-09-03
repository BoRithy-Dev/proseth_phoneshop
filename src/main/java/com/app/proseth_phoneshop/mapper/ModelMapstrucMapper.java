package com.app.proseth_phoneshop.mapper;

import com.app.proseth_phoneshop.dto.BrandDTO;
import com.app.proseth_phoneshop.dto.ModelDTO;
import com.app.proseth_phoneshop.entity.Brand;
import com.app.proseth_phoneshop.entity.Model;
import com.app.proseth_phoneshop.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ModelMapstrucMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", ignore = true)
    Model toModelEntity(ModelDTO dto);

    @Mapping(target = "brand_id", source = "brand.id")
    ModelDTO toModelDTO(Model model);
}
