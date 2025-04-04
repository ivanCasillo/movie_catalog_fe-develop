package it.step.dto;

import it.step.entity.Genre;
import it.step.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {

    private Integer idGenre;
    private String genre;

}
