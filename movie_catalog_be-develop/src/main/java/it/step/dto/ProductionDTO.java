package it.step.dto;

import it.step.entity.Movie;
import it.step.entity.Production;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionDTO {

    private Integer idProduction;
    private String name;

}
