package it.step.controller;

import io.swagger.annotations.Api;
import it.step.dto.MovieDetailsDTO;
import it.step.entity.Movie;
import it.step.entity.MovieListResponse;
import it.step.mapper.MovieMapperImpl;
import it.step.mapper.impl.MovieDetailsMapperImpl;
import it.step.service.MovieService;
import it.step.service.impl.MovieServiceImpl;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@Api(tags = "Movie controller")
@RestController
@RequestMapping("/movies")
@Transactional
public class MovieController {

	@Autowired
	private MovieService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
	
	@PostMapping(value = "/update")
	public ResponseEntity<MovieDetailsDTO> updateMovie(@RequestBody MovieDetailsDTO movie){

		ResponseEntity<MovieDetailsDTO> response = null;
		try {
			MovieDetailsDTO movieRes = service.updateMovieById(movie.getMovieDto().getImdbid(), movie).orElse(null);
			if(movieRes != null)
				response = new ResponseEntity<MovieDetailsDTO>(movieRes, HttpStatus.OK);
			else
				response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

	@PostMapping(value = "/add")
	public ResponseEntity<MovieDetailsDTO> saveMovie(@RequestBody MovieDetailsDTO movie){

		ResponseEntity<MovieDetailsDTO> response = null;
		LOGGER.info(movie.toString());

		try {
			Movie movieRes = service.saveMovie(movie);
			MovieDetailsDTO dto = MovieDetailsMapperImpl.movieToMovieDetailsDTO(movieRes);
			response = new ResponseEntity<MovieDetailsDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
			e.printStackTrace();
			response = new ResponseEntity<MovieDetailsDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	@GetMapping(value = "/details/{id}")
	public ResponseEntity<MovieDetailsDTO> getMovieById(@PathVariable("id") String id){
		
		ResponseEntity<MovieDetailsDTO> response = null;
		try {
			Optional<MovieDetailsDTO> optMovie = service.getMovieById(id);
			if(optMovie.isPresent())
				response = new ResponseEntity<MovieDetailsDTO>(optMovie.get(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			response = new ResponseEntity<MovieDetailsDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

	@GetMapping(value = {"/all/{page}", "/all", "/all/{page}/{sort}",  "/all/{page}/{sort}/{size}"})
	public ResponseEntity<MovieListResponse> getAll(
			@PathVariable("page") Optional<Integer> page,
			@PathVariable("sort") Optional<String> sort,
			@PathVariable("size") Optional<Integer> size) {

		LOGGER.info(" --- Entering getAll()");

		ResponseEntity<MovieListResponse> response = null;
		MovieListResponse res = new MovieListResponse();
		try {
			res.setMovieList(service.getAll(page.orElse(1), sort.orElse("rating"), size.orElse(10)));
			res.setMaxPageNumber(service.getNumPagesAll(size.orElse(10)));
			res.setTotalElements(service.getNumElemAll());

			if (res.getMovieList().size() > 0)
				response = new ResponseEntity<>(res, HttpStatus.OK);
			else
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	@GetMapping(value = {"/genre/{genre}/{page}","/genre/{genre}","size/{genre}/{page}/{size}"})
	public ResponseEntity<MovieListResponse> getByGenre(@PathVariable("genre") Integer genre,
													@PathVariable("page") Optional<Integer> page,
														@PathVariable("size") Optional<Integer> size){

		ResponseEntity<MovieListResponse> response = null;
		MovieListResponse res = new MovieListResponse();
		try {
			res.setMovieList(service.getByGenre(genre, page.orElse(1), size.orElse(10)));
			res.setMaxPageNumber(service.getNumPagesByGenre(genre));
			res.setTotalElements(service.getNumElemByGenre(genre));
			
			if(res.getMovieList().size()>0)
				response = new ResponseEntity<MovieListResponse>(res, HttpStatus.OK);
			else
				response = new ResponseEntity<MovieListResponse>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			response = new ResponseEntity<MovieListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@GetMapping(value = {"/title/{title}/{page}", "/title/{title}","/title/{title}/{page}/{size}"})
	public ResponseEntity<MovieListResponse> getByTitle(@PathVariable("title") String title,
													@PathVariable("page") Optional<Integer> page,
														@PathVariable("size") Optional<Integer> size){

		ResponseEntity<MovieListResponse> response = null;
		MovieListResponse res = new MovieListResponse();
		
		try {
				res.setMovieList(service.searchTitle(title, page.orElse(1),size.orElse(10)));
				res.setMaxPageNumber(service.getNumPagesByTitle(title, size.orElse(10)));
				res.setTotalElements(service.getNumElemByTitle(title));
			if(res.getMovieList().size()>0)
				response = new ResponseEntity<MovieListResponse>(res, HttpStatus.OK);
			else
				response = new ResponseEntity<MovieListResponse>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			response = new ResponseEntity<MovieListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<MovieDetailsDTO> updateMovie(@PathVariable("id") String id, @RequestBody MovieDetailsDTO movie){
//
//		ResponseEntity<MovieDetailsDTO> response = null;
//		try {
//			Optional<MovieDetailsDTO> optMovie = service.updateMovieById(id, movie);
//			if(optMovie.isPresent())
//				response = new ResponseEntity<MovieDetailsDTO>(optMovie.get(), HttpStatus.OK);
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			e.printStackTrace();
//			response = new ResponseEntity<MovieDetailsDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		return response;
//	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MovieDetailsDTO> deleteMovieById(@PathVariable("id") String id){
		ResponseEntity<MovieDetailsDTO> response = null;
		try {
			Optional<MovieDetailsDTO> optMovie = service.deleteMovieById(id);
			if(optMovie.isPresent())
				response = new ResponseEntity<MovieDetailsDTO>(optMovie.get(), HttpStatus.OK);
			else if (optMovie.isEmpty()) {
				response = new ResponseEntity<MovieDetailsDTO>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response = new ResponseEntity<MovieDetailsDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
}
