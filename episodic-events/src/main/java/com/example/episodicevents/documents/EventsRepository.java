package com.example.episodicevents.documents;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by trainer20 on 5/21/17.
 */
public interface EventsRepository extends MongoRepository<Event,Long> {

    @Query("{}")
    public List<Event> findRecent(Pageable page);

}
