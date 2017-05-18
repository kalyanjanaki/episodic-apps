package com.example.episodes;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by trainer20 on 5/18/17.
 */
public interface EpisodeRepository extends CrudRepository<Episode,Long>{

    Iterable<Episode> findByShowId(Long ShowId);
}
