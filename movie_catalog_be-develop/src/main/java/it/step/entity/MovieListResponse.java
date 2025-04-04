package it.step.entity;

import it.step.dto.MovieDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieListResponse {

	List<MovieDetailsDTO> movieList;
	Integer maxPageNumber;
	Integer size;
	Integer totalElements;
	
	
}
