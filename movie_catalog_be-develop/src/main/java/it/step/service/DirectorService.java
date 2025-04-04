package it.step.service;

import it.step.entity.Director;

import java.util.List;

public interface DirectorService {

    public List<Director> getAllDirectors();

    public Director saveDirector(Director director);
}
