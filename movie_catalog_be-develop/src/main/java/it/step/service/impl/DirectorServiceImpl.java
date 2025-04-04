package it.step.service.impl;

import it.step.entity.Director;
import it.step.repository.DirectorRepository;
import it.step.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorRepository repo;


    @Override
    public List<Director> getAllDirectors() {
        return repo.findAll();
    }

    @Override
    public Director saveDirector(Director director) {
        return repo.save(director);
    }
}
