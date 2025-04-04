package it.step.repository;

import it.step.dto.MovieDetailsDTO;
import it.step.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String>{

	Page<Movie> findByTitleContainingIgnoreCase(String title, PageRequest page);
	Page<Movie> findByGenresIdGenre(Integer genre, PageRequest page);
	
	List<Movie> findAllByTitleContainingIgnoreCase(String title);
	List<Movie> findAllByGenresIdGenre(Integer genre);
}
