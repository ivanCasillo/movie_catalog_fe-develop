package it.step.controller;

import io.swagger.annotations.Api;
import it.step.dto.LanguageDTO;
import it.step.entity.Language;
import it.step.mapper.LanguageMapper;
import it.step.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "Language controller")
@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageController.class);

    @GetMapping("all")
    public ResponseEntity<List<LanguageDTO>> getAllLanguages() {
        try {
            List<Language> languages = service.getAllLanguages();
            List<LanguageDTO> languageDTOs = new ArrayList<>();

            languages.forEach(language -> {
                LanguageDTO dto = Mappers.getMapper(LanguageMapper.class).languageToLanguageDto(language);
                languageDTOs.add(dto);
            });

            return new ResponseEntity<>(languageDTOs, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "add")
    public ResponseEntity<LanguageDTO> saveWriter(@RequestBody LanguageDTO languageDTO) {
        try {
            Language language = Mappers.getMapper(LanguageMapper.class).languageDtoToLanguage(languageDTO);
            language = service.saveLanguage(language);
            languageDTO.setIdLanguage(language.getIdLanguage());
            return new ResponseEntity<>(languageDTO, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
