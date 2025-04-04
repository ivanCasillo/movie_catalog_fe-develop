package it.step.mapper;

import it.step.dto.MovieDTO;
import it.step.entity.Movie;
import org.mapstruct.Mapper;

@Mapper
public interface MovieMapper {

    MovieDTO movieToMovieDto(Movie movie);
    Movie movieDtoToMovie(MovieDTO movieDto);

}
