package it.step.dto;

import it.step.entity.Actor;
import it.step.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorDTO {

    private Integer idActor;
    private String first_name;
    private String last_name;

}
