package it.step.mapper;

import it.step.dto.WriterDTO;
import it.step.entity.Writer;
import org.mapstruct.Mapper;

@Mapper
public interface WriterMapper {

    Writer writerDtoToWriter(WriterDTO writerDto);
    WriterDTO writerToWriterDto(Writer writer);

}
