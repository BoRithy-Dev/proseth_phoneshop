package com.app.proseth_phoneshop.mapper;

import com.app.proseth_phoneshop.dto.BrandDTO;
import com.app.proseth_phoneshop.entity.Brand;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BrandMapstrucMapper {
    Brand toBrandEntity(BrandDTO brandDTO);
    BrandDTO toBrandDTO(Brand brand);
}
