package it.step.service;

import it.step.entity.Production;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductionService {

    public List<Production> getAllProductions();

    public Production saveProduction(Production production);
}
