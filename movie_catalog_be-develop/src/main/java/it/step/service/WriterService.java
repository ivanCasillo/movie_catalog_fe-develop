package it.step.service;

import it.step.entity.Writer;

import java.util.List;

public interface WriterService {

    public List<Writer> getAllWriters();

    public Writer saveWriter(Writer writer);
}
