package it.step.controller;

import io.swagger.annotations.Api;
import it.step.dto.CountryDTO;
import it.step.dto.WriterDTO;
import it.step.entity.Country;
import it.step.entity.Writer;
import it.step.mapper.CountryMapper;
import it.step.mapper.WriterMapper;
import it.step.service.CountryService;
import it.step.service.WriterService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "Country controller")
@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @GetMapping("all")
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        try {
            List<Country> countries = service.getAllCountries();
            List<CountryDTO> countryDTOs = new ArrayList<>();

            countries.forEach(country -> {
                CountryDTO dto = Mappers.getMapper(CountryMapper.class).countryToCountryDto(country);
                countryDTOs.add(dto);
            });

            return new ResponseEntity<>(countryDTOs, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "add")
    public ResponseEntity<CountryDTO> saveCountry(@RequestBody CountryDTO countryDTO) {
        try {
            Country country = Mappers.getMapper(CountryMapper.class).countryDtoToCountry(countryDTO);
            country = service.saveCountry(country);
            countryDTO.setIdCountry(country.getIdCountry());
            return new ResponseEntity<>(countryDTO, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
