package it.step.controller;

import io.swagger.annotations.Api;
import it.step.dto.GenreDTO;
import it.step.dto.TypeDTO;
import it.step.entity.Genre;
import it.step.entity.Type;
import it.step.mapper.GenreMapper;
import it.step.mapper.TypeMapper;
import it.step.service.GenreService;
import it.step.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "Type controller")
@RestController
@RequestMapping("/types")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeController.class);

    @GetMapping("/all")
    public ResponseEntity<List<TypeDTO>> getAllTypes(){
        try{
            List<Type> types = service.getAllTypes();
            List<TypeDTO> typeDTOs = new ArrayList<>();

            types.forEach(type -> {
                TypeDTO dto = Mappers.getMapper(TypeMapper.class).typeToTypeDto(type);
                typeDTOs.add(dto);
            });

            return new ResponseEntity<>(typeDTOs, HttpStatus.OK);

        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
