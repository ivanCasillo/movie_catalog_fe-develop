package it.step.mapper;

import it.step.dto.ProductionDTO;
import it.step.entity.Production;
import org.mapstruct.Mapper;

@Mapper
public interface ProductionMapper {

    Production productionDtoToProduction(ProductionDTO productionDto);
    ProductionDTO productionToProductionDto(Production production);

}
