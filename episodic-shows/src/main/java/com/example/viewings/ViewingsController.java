package com.example.viewings;

import com.example.episodes.Episode;
import com.example.episodes.EpisodeRepository;
import com.example.shows.Show;
import com.example.shows.ShowsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trainer20 on 5/18/17.
 */
@RestController
public class ViewingsController {

    private final ViewingRepository viewingRepository;
    private final EpisodeRepository episodeRepository;
    private final ShowsRepository showsRepository;

    public ViewingsController(ViewingRepository viewingRepository, EpisodeRepository episodeRepository,
                              ShowsRepository showsRepository) {
        this.viewingRepository = viewingRepository;
        this.episodeRepository = episodeRepository;
        this.showsRepository = showsRepository;
    }

    @PatchMapping("/users/{id}/viewings")
    public void patchViewing(@PathVariable Long id, @RequestBody Viewing viewing){

        Episode episode = episodeRepository.findOne(viewing.getEpisodeId());
        Viewing viewing1 = viewingRepository.findByUserIdAndShowIdAndAndEpisodeId(id,episode.getShowId(),viewing.getEpisodeId());
        if(viewing1 == null){
            viewing.setUserId(id);
            viewing.setShowId(episode.getShowId());
            viewingRepository.save(viewing);
        }else{
            viewing1.setUpdatedAt(viewing.getUpdatedAt());
            viewing1.setTimecode(viewing.getTimecode());
            viewingRepository.save(viewing1);
        }
    }

    @GetMapping("/users/{id}/recently-watched")
    public List<RecentViewing> getRecentViewing(@PathVariable Long id){

        List<RecentViewing> result = new ArrayList<RecentViewing>();

        List<Viewing> viewresults = viewingRepository.findByUserIdOrderByUpdatedAtDesc(id);
        for(Viewing view : viewresults){
            RecentViewing r = new RecentViewing();
            r.setShow(showsRepository.findOne(view.getShowId()));
            r.setEpisode(episodeRepository.findOne(view.getEpisodeId()));
            r.setUpdatedAt(view.getUpdatedAt());
            r.setTimecode(view.getTimecode());
            result.add(r);
        }
        return result;
    }
}
