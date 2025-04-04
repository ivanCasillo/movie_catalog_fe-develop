package it.step.service.impl;

import it.step.entity.Production;
import it.step.repository.ProductionRepository;
import it.step.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductionServiceImpl implements ProductionService {

    @Autowired
    private ProductionRepository repo;

    @Override
    public List<Production> getAllProductions() {
        return repo.findAll();
    }

    @Override
    public Production saveProduction(Production production) {
        return repo.save(production);
    }
}
