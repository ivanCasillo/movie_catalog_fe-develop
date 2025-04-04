package it.step.service.impl;

import it.step.entity.Language;
import it.step.repository.LanguageRepository;
import it.step.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository repo;


    @Override
    public List<Language> getAllLanguages() {
        return repo.findAll();
    }

    @Override
    public Language saveLanguage(Language language) {
        return repo.save(language);
    }
}
