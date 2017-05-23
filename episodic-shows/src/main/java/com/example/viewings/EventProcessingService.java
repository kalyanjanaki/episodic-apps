package com.example.viewings;

import com.example.episodes.Episode;
import com.example.episodes.EpisodeRepository;
import com.example.shows.ShowsRepository;
import com.example.users.User;
import com.example.users.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Created by trainer20 on 5/22/17.
 */
@Service
public class EventProcessingService {

    private final ViewingRepository viewingRepository;
    private final ShowsRepository showsRepository;
    private final EpisodeRepository episodeRepository;
    private final UserRepository userRepository;

    public EventProcessingService(ViewingRepository viewingRepository,
                                  ShowsRepository showsRepository,
                                  EpisodeRepository episodeRepository,
                                  UserRepository userRepository) {
        this.viewingRepository = viewingRepository;
        this.showsRepository = showsRepository;
        this.episodeRepository = episodeRepository;
        this.userRepository = userRepository;
    }


    public void upsertViewing(EventMessage message) throws Exception {
        Episode episode = episodeRepository.findOne(message.getEpisodeId());
        if(episode != null){

            User user = userRepository.findOne(message.getUserId());
            if(user != null){

                Viewing viewing = viewingRepository.findByUserIdAndShowIdAndAndEpisodeId(message.getUserId(),
                        episode.getShowId(),message.getEpisodeId());
                if(viewing != null){

                    viewing.setUpdatedAt(message.getCreatedAt());
                    viewing.setTimecode(message.getOffset());
                    viewingRepository.save(viewing);
                }else{
                    Viewing viewing1 = new Viewing();
                    viewing1.setUserId(message.getUserId());
                    viewing1.setShowId(episode.getShowId());
                    viewing1.setEpisodeId(message.getEpisodeId());
                    viewing1.setTimecode(message.getOffset());
                    viewing1.setUpdatedAt(message.getCreatedAt());
                    viewingRepository.save(viewing1);
                }

            }else{
                throw new Exception("Invalid User Id");
            }

        }else {
            throw new Exception("Invalid Episode ID");
        }

    }
}
