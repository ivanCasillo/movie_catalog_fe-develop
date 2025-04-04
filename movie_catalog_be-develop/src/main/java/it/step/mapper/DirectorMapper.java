package it.step.mapper;

import it.step.dto.DirectrorDTO;
import it.step.entity.Director;
import org.mapstruct.Mapper;

@Mapper
public interface DirectorMapper {

    Director directorDtoToDirector(DirectrorDTO directrorDto);
    DirectrorDTO directorToDirectorDto(Director director);

}
