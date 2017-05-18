package com.example.episodes;

import org.springframework.web.bind.annotation.*;

/**
 * Created by trainer20 on 5/18/17.
 */
@RestController
public class EpisodeController {

    private final EpisodeRepository episodeRepository;

    public EpisodeController(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    @GetMapping("/shows/{id}/episodes")
    public Iterable<Episode> getEpisodes(@PathVariable Long id){
        return episodeRepository.findByShowId(id);
    }

    @PostMapping("/shows/{id}/episodes")
    public Episode createEpisode(@RequestBody Episode episode,@PathVariable Long id){
        episode.setShowId(id);
        return episodeRepository.save(episode);
    }
}
