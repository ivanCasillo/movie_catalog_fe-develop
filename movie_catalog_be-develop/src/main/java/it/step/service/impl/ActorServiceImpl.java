package it.step.service.impl;

import it.step.entity.Actor;
import it.step.repository.ActorRepository;
import it.step.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository repo;

    @Override
    public List<Actor> getAllActors() {
        return repo.findAll();
    }

    @Override
    public Optional<Actor> getActorById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Actor saveActor(Actor actor) {
        return repo.save(actor);
    }

    @Override
    public Optional<Actor> deleteActorById(Integer id) {
        Optional<Actor> actorRes = getActorById(id);
        if(actorRes.isPresent()){
            repo.deleteById(actorRes.get().getIdActor());
        }
        return actorRes;
    }
}
