package com.app.proseth_phoneshop.mapper;

import com.app.proseth_phoneshop.dto.ProductDTO;
import com.app.proseth_phoneshop.entity.Product;
import com.app.proseth_phoneshop.service.ColorService;
import com.app.proseth_phoneshop.service.ModelService;
import com.app.proseth_phoneshop.service.ProductService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")

public interface ProductMapstructMapper {
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "color", ignore = true)
    Product toProductEntity(ProductDTO productDTO);

    @Mapping(source = "model.id", target = "modelId")
    @Mapping(source = "color.id", target = "colorId")
    ProductDTO toProductDTO(Product product);
}
