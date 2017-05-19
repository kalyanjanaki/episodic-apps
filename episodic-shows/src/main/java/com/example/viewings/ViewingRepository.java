package com.example.viewings;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by trainer20 on 5/18/17.
 */
public interface ViewingRepository extends CrudRepository<Viewing,Long> {
    Viewing findByUserIdAndShowIdAndAndEpisodeId(Long userId,Long showId,Long episodeId);

    List<Viewing> findByUserIdOrderByUpdatedAtDesc(Long userId);
}
