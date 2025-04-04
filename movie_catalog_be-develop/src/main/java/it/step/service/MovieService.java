package it.step.service;

import it.step.dto.MovieDTO;
import it.step.dto.MovieDetailsDTO;
import it.step.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    public Integer getNumPagesAll(int size);

    public Integer getNumElemAll();

    public Integer getNumPagesByTitle(String title, int size);

    public Integer getNumElemByTitle(String title);

    public Integer getNumPagesByGenre(Integer genre);

    public Integer getNumElemByGenre(Integer genre);

    public List<MovieDetailsDTO> getAll(Integer page, String sort, Integer size);

    public Optional<MovieDetailsDTO> getMovieById(String id);

    public List<MovieDetailsDTO> getByGenre(Integer genre, Integer page, Integer size);

    public List<MovieDetailsDTO> searchTitle(String title, Integer page, Integer size);

    public Movie saveMovie(MovieDetailsDTO movie);

    public Optional<MovieDetailsDTO> updateMovieById(String id, MovieDetailsDTO movie);

    public Optional<MovieDetailsDTO> deleteMovieById(String id);
}
