package it.step.service.impl;

import it.step.entity.Country;
import it.step.repository.CountryRepository;
import it.step.repository.WriterRepository;
import it.step.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repo;

    @Override
    public List<Country> getAllCountries() {
        return repo.findAll();
    }

    @Override
    public Country saveCountry(Country country) {
        return repo.save(country);
    }
}
