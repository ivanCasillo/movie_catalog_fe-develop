package it.step.service.impl;

import it.step.dto.*;
import it.step.entity.Actor;
import it.step.entity.Movie;
import it.step.mapper.*;
import it.step.mapper.impl.MovieDetailsMapperImpl;
import it.step.repository.MovieRepository;
import it.step.service.MovieService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository movieRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

	@Override
	public List<MovieDetailsDTO> getAll(Integer page, String sort, Integer size) {
		if (sort.equals("rating")) {
			List<Movie> movieLst =  movieRepository.findAll(PageRequest.of(--page, size, Sort.by(sort).descending())).toList();
			return MovieDetailsMapperImpl.movieLstToMovieDetailsDTOLst(movieLst);

		} else if (sort.contains("asc")) {
			List<Movie> movieLst = movieRepository.findAll(PageRequest.of(--page, size, Sort.by(sort.split("-")[0]))).toList();
			return MovieDetailsMapperImpl.movieLstToMovieDetailsDTOLst(movieLst);

		} else if (sort.contains("desc")) {
			List<Movie> movieLst = movieRepository.findAll(PageRequest.of(--page, size, Sort.by(sort.split("-")[0]).descending())).toList();
			return MovieDetailsMapperImpl.movieLstToMovieDetailsDTOLst(movieLst);

		} else {
			List<Movie> movieLst = movieRepository.findAll(PageRequest.of(--page, size, Sort.by(sort))).toList();
			return MovieDetailsMapperImpl.movieLstToMovieDetailsDTOLst(movieLst);
		}
	}

	@Override
	public Optional<MovieDetailsDTO> getMovieById(String id) {
		Optional<Movie> movie = movieRepository.findById(id);
		if(movie.isPresent()){
			return Optional.of(MovieDetailsMapperImpl.movieToMovieDetailsDTO(movie.get()));
		}
		throw new RuntimeException("Movie not found");
	}

	@Override
	public List<MovieDetailsDTO> getByGenre(Integer genre, Integer page, Integer size) {
		List<Movie> movieLst = movieRepository.findByGenresIdGenre(genre, PageRequest.of(--page, size)).toList();
		return MovieDetailsMapperImpl.movieLstToMovieDetailsDTOLst(movieLst);
	}

	@Override
	public List<MovieDetailsDTO> searchTitle(String title, Integer page,Integer size) {
		List<Movie> movieLst = movieRepository.findByTitleContainingIgnoreCase(title, PageRequest.of(--page, size, Sort.by("rating").descending())).toList();
		return MovieDetailsMapperImpl.movieLstToMovieDetailsDTOLst(movieLst);
	}

	@Override
	public Movie saveMovie(MovieDetailsDTO dto) {
		Movie movie = MovieDetailsMapperImpl.movieDetailsDTOToMovie(dto);
		System.out.println(movie.getImdbid());
		return movieRepository.save(movie);
	}

	@Override
	public Optional<MovieDetailsDTO> updateMovieById(String id, MovieDetailsDTO dto) {

		Optional<MovieDetailsDTO> movieRes = getMovieById(id);
		if(movieRes.isPresent()) {
			Movie movie = MovieDetailsMapperImpl.movieDetailsDTOToMovie(dto);
			movieRepository.saveAndFlush(movie);
		}
		return movieRes;
		
	}

	@Override
	public Optional<MovieDetailsDTO> deleteMovieById(String id) {
		
		Optional<Movie> movieRes = null;
		
		if(movieRepository.findById(id).isPresent()) {
			movieRes = movieRepository.findById(id);
			movieRepository.deleteById(id);
		}
		return Optional.of(MovieDetailsMapperImpl.movieToMovieDetailsDTO(movieRes.get()));
	}

	@Override
	public Integer getNumPagesAll(int size) {
		Integer numElem = movieRepository.findAll().size();
		return (int) Math.ceil((double)numElem/size);
	}

	@Override
	public Integer getNumElemAll(){
		return movieRepository.findAll().size();
	}
	
	@Override
	public Integer getNumPagesByTitle(String title, int size) {
		Integer numElem = movieRepository.findAllByTitleContainingIgnoreCase(title).size();
		return (int) Math.ceil((double)numElem/size);
	}

	@Override
	public Integer getNumElemByTitle(String title){
		return movieRepository.findAllByTitleContainingIgnoreCase(title).size();
	}
	
	@Override
	public Integer getNumPagesByGenre(Integer genre) {
		Integer numElem = movieRepository.findAllByGenresIdGenre(genre).size();
		return (int) Math.ceil((double)numElem/10);
	}

	@Override
	public Integer getNumElemByGenre(Integer genre){
		return movieRepository.findAllByGenresIdGenre(genre).size();
	}
}
