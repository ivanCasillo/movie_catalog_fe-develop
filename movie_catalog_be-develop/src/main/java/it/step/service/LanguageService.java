package it.step.service;

import it.step.entity.Language;

import java.util.List;

public interface LanguageService {

    public List<Language> getAllLanguages();

    public Language saveLanguage(Language language);
}
