package it.step.controller;

import io.swagger.annotations.Api;
import it.step.dto.GenreDTO;
import it.step.entity.Actor;
import it.step.entity.Genre;
import it.step.mapper.GenreMapper;
import it.step.service.ActorService;
import it.step.service.GenreService;
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

@Api(tags = "Genre controller")
@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(GenreController.class);

    @GetMapping("/all")
    public ResponseEntity<List<GenreDTO>> getAllGenres(){
       try{
           List<Genre> genres = service.getAllGenres();
           List<GenreDTO> genreDTOs = new ArrayList<>();

           genres.forEach(genre -> {
               GenreDTO dto = Mappers.getMapper(GenreMapper.class).genreToGenreDto(genre);
               genreDTOs.add(dto);
           });

           return new ResponseEntity<>(genreDTOs, HttpStatus.OK);

       }catch (Exception e){
           LOGGER.error(e.getMessage());
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}
