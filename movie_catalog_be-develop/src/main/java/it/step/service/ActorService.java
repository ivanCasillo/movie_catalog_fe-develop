package it.step.service;

import it.step.entity.Actor;
import org.hibernate.type.IntegerType;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    public List<Actor> getAllActors();

    public Optional<Actor> getActorById(Integer id);

    public Actor saveActor(Actor actor);

    public Optional<Actor> deleteActorById(Integer id);
}
