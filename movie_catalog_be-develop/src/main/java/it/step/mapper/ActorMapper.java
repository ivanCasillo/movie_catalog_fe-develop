package it.step.mapper;

import it.step.dto.ActorDTO;
import it.step.entity.Actor;
import org.mapstruct.Mapper;

@Mapper
public interface ActorMapper {

    ActorDTO actorToActorDto(Actor actor);
    Actor actorDtoToActor(ActorDTO actorDto);

}
