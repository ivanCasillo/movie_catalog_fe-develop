package it.step.mapper;

import it.step.dto.CountryDTO;
import it.step.entity.Country;
import org.mapstruct.Mapper;

@Mapper
public interface CountryMapper {

    CountryDTO countryToCountryDto(Country country);
    Country countryDtoToCountry (CountryDTO countryDto);
}
