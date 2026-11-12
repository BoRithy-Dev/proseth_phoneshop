package com.app.proseth_phoneshop.mapper;
import com.app.proseth_phoneshop.dto.ColorDTO;
import com.app.proseth_phoneshop.entity.Color;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface ColorMapstrucMapper {
    ColorMapstrucMapper INSTANCE = Mappers.getMapper(ColorMapstrucMapper.class);
    Color toColorEntity(ColorDTO colorDTO);
    ColorDTO toColorDTO(Color color);
}
