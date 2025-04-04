package it.step.service;

import it.step.entity.Country;
import it.step.entity.Writer;

import java.util.List;

public interface CountryService {

    public List<Country> getAllCountries();

    public Country saveCountry(Country country);
}
