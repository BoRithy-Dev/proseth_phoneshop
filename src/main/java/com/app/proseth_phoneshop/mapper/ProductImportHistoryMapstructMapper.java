package com.app.proseth_phoneshop.mapper;

import com.app.proseth_phoneshop.dto.ProductImportDTO;
import com.app.proseth_phoneshop.entity.ProductImportHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface ProductImportHistoryMapstructMapper {
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    ProductImportHistory toProductImportHistoryEntity(ProductImportDTO productImportDTO);

    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "product.id", target = "productId")
    ProductImportDTO toProductImportHistoryDTO(ProductImportHistory productImportHistory);
}
