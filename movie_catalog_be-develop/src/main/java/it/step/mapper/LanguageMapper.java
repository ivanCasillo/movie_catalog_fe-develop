package it.step.mapper;

import it.step.dto.LanguageDTO;
import it.step.entity.Language;
import org.mapstruct.Mapper;

@Mapper
public interface LanguageMapper {

    Language languageDtoToLanguage(LanguageDTO languageDto);
    LanguageDTO languageToLanguageDto (Language language);
}
