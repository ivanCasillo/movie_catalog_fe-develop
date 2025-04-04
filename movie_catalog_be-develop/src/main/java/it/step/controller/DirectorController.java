package it.step.controller;

import io.swagger.annotations.Api;
import it.step.dto.ActorDTO;
import it.step.dto.DirectrorDTO;
import it.step.entity.Actor;
import it.step.entity.Director;
import it.step.mapper.ActorMapper;
import it.step.mapper.DirectorMapper;
import it.step.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "Director controller")
@RestController
@RequestMapping("/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectorController.class);

    @GetMapping("all")
    public ResponseEntity<List<DirectrorDTO>> getAllDirectors() {
        try {
            List<Director> directors = service.getAllDirectors();
            List<DirectrorDTO> directrorDTOs = new ArrayList<>();

            directors.forEach(director -> {
                DirectrorDTO dto = Mappers.getMapper(DirectorMapper.class).directorToDirectorDto(director);
                directrorDTOs.add(dto);
            });

            return new ResponseEntity<>(directrorDTOs, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "add")
    public ResponseEntity<DirectrorDTO> saveDirector(@RequestBody DirectrorDTO directorDTO) {
        try {
            Director director = Mappers.getMapper(DirectorMapper.class).directorDtoToDirector(directorDTO);
            director = service.saveDirector(director);
            directorDTO.setIdDirector(director.getIdDirector());
            return new ResponseEntity<>(directorDTO, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
