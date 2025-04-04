package it.step.service.impl;

import it.step.entity.Writer;
import it.step.repository.WriterRepository;
import it.step.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WriterServiceImpl implements WriterService {

    @Autowired
    private WriterRepository repo;


    @Override
    public List<Writer> getAllWriters() {
        return repo.findAll();
    }

    @Override
    public Writer saveWriter(Writer writer) {
        return repo.save(writer);
    }
}
