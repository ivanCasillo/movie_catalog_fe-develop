package it.step.mapper;

import it.step.dto.GenreDTO;
import it.step.entity.Genre;
import org.mapstruct.Mapper;

@Mapper
public interface GenreMapper {

    Genre genreDtoToGenre(GenreDTO genreDto);
    GenreDTO genreToGenreDto(Genre genre);
}
