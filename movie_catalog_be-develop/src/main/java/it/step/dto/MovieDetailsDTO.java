package it.step.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.ManagedBean;
import javax.persistence.Entity;
import java.lang.invoke.CallSite;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailsDTO {

//    Movie Entity
    private MovieDTO movieDto;

//    Actor Entity
    private List<ActorDTO> actorDTOs;

//    Country Entity
    private List<CountryDTO> countryDTOs;

//    Director Entity
    private List<DirectrorDTO> directrorDTOs;

//    Genre Entity
    private List<GenreDTO> genreDTOs;

//    Language Entity
    private List<LanguageDTO> languageDTOs;

//    Production Entity
    private List<ProductionDTO> productionDTOs;

//    Writer Entity
    private List<WriterDTO> writerDTOs;

}
