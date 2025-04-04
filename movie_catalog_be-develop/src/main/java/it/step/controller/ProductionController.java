package it.step.controller;

import io.swagger.annotations.Api;
import it.step.dto.ProductionDTO;
import it.step.entity.Production;
import it.step.mapper.ProductionMapper;
import it.step.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "Production controller")
@RestController
@RequestMapping("/productions")
@RequiredArgsConstructor
public class ProductionController {

    private final ProductionService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductionController.class);

    @GetMapping("all")
    public ResponseEntity<List<ProductionDTO>> getAllProductions() {
        try {
            List<Production> productions = service.getAllProductions();
            List<ProductionDTO> productionDTOs = new ArrayList<>();

            productions.forEach(production -> {
                ProductionDTO dto = Mappers.getMapper(ProductionMapper.class).productionToProductionDto(production);
                productionDTOs.add(dto);
            });

            return new ResponseEntity<>(productionDTOs, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "add")
    public ResponseEntity<ProductionDTO> saveProduction(@RequestBody ProductionDTO productionDTO) {
        try {
            Production production = Mappers.getMapper(ProductionMapper.class).productionDtoToProduction(productionDTO);
            production = service.saveProduction(production);
            productionDTO.setIdProduction(production.getIdProduction());
            return new ResponseEntity<>(productionDTO, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
