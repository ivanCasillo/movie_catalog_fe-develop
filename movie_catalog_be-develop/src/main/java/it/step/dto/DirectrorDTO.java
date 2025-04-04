package it.step.dto;

import it.step.entity.Director;
import it.step.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectrorDTO {

    @Column(name = "id_director")
    private Integer idDirector;
    private String first_name;
    private String last_name;
    
}
