package it.step.dto;

import it.step.entity.Country;
import it.step.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {

    @Column(name = "id_country")
    private Integer idCountry;
    private String countries;

}