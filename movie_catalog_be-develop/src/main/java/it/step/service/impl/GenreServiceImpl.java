package it.step.service.impl;

import it.step.entity.Genre;
import it.step.repository.GenreRepository;
import it.step.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository repo;

    @Override
    public List<Genre> getAllGenres() {
        return repo.findAll();
    }
}
