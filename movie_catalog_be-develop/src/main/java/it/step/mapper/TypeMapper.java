package it.step.mapper;

import it.step.dto.TypeDTO;
import it.step.entity.Type;
import org.mapstruct.Mapper;

@Mapper
public interface TypeMapper {

    Type typeDtoToType(TypeDTO typeDTO);
    TypeDTO typeToTypeDto(Type type);

}
