package it.step.service.impl;

import it.step.entity.Type;
import it.step.repository.TypeRepository;
import it.step.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository repo;

    @Override
    public List<Type> getAllTypes() {
        return repo.findAll();
    }

}
