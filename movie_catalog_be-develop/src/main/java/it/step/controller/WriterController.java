package it.step.controller;

import io.swagger.annotations.Api;
import it.step.dto.DirectrorDTO;
import it.step.dto.WriterDTO;
import it.step.entity.Director;
import it.step.entity.Writer;
import it.step.mapper.DirectorMapper;
import it.step.mapper.WriterMapper;
import it.step.service.DirectorService;
import it.step.service.WriterService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "Writer controller")
@RestController
@RequestMapping("/writers")
@RequiredArgsConstructor
public class WriterController {

    private final WriterService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(WriterController.class);

    @GetMapping("all")
    public ResponseEntity<List<WriterDTO>> getAllWriters() {
        try {
            List<Writer> writers = service.getAllWriters();
            List<WriterDTO> writerDTOs = new ArrayList<>();

            writers.forEach(writer -> {
                WriterDTO dto = Mappers.getMapper(WriterMapper.class).writerToWriterDto(writer);
                writerDTOs.add(dto);
            });

            return new ResponseEntity<>(writerDTOs, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "add")
    public ResponseEntity<WriterDTO> saveWriter(@RequestBody WriterDTO writerDTO) {
        try {
            Writer writer = Mappers.getMapper(WriterMapper.class).writerDtoToWriter(writerDTO);
            writer = service.saveWriter(writer);
            writerDTO.setIdWriter(writer.getIdWriter());
            return new ResponseEntity<>(writerDTO, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
