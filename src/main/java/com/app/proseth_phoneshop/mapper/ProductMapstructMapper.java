package com.app.proseth_phoneshop.mapper;

import com.app.proseth_phoneshop.dto.ProductDTO;
import com.app.proseth_phoneshop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapstrucMapper {

    @Mapping(source = "model_id", target = "modelId")
    @Mapping(source = "color_id", target = "colorId")
    Product toProductEntity(ProductDTO dto);
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "color", ignore = true)
    ProductDTO toProductDTO(Product product);
}
