package it.step.controller;

import io.swagger.annotations.Api;
import it.step.dto.ActorDTO;
import it.step.entity.Actor;
import it.step.mapper.ActorMapper;
import it.step.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(tags = "Actor controller")
@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(ActorController.class);

    @GetMapping("get/{id}")
    public ResponseEntity<ActorDTO> getActorById(@PathVariable("id") Integer id) {
        try {
            Optional<Actor> actor = service.getActorById(id);
            if (actor.isPresent()) {
                ActorDTO actorDTO = Mappers.getMapper(ActorMapper.class).actorToActorDto(actor.get());
                return new ResponseEntity<>(actorDTO, HttpStatus.OK);
            }
            throw new RuntimeException();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<ActorDTO>> getAllActors() {
        try {
            List<Actor> actors = service.getAllActors();
            List<ActorDTO> actorDTOs = new ArrayList<>();

            actors.forEach(actor -> {
                ActorDTO dto = Mappers.getMapper(ActorMapper.class).actorToActorDto(actor);
                actorDTOs.add(dto);
            });

            return new ResponseEntity<>(actorDTOs, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "add")
    public ResponseEntity<ActorDTO> saveActor(@RequestBody ActorDTO actorDTO) {
        try {
            Actor actor = Mappers.getMapper(ActorMapper.class).actorDtoToActor(actorDTO);
            actor = service.saveActor(actor);
            actorDTO.setIdActor(actor.getIdActor());
            return new ResponseEntity<>(actorDTO, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
