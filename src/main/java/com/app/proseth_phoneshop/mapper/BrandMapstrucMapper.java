package com.app.proseth_phoneshop.mapper;

import com.app.proseth_phoneshop.dto.BrandDTO;
import com.app.proseth_phoneshop.entity.Brand;
import com.app.proseth_phoneshop.util.MapperBrand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface BrandMapstrucMapper {
//    BrandMapstrucMapper INSTANCE = Mappers.getMapper(BrandMapstrucMapper.class);
    Brand toBrandEntity(BrandDTO brandDTO);
    BrandDTO toBrandDTO(Brand brand);
}
