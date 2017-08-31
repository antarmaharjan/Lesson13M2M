package me.ratna.springboot13.repositories;

import me.ratna.springboot13.models.Actor;
import me.ratna.springboot13.models.Movie;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;
import java.util.Set;


public interface ActorRepo extends CrudRepository<Actor,Long> {

}
