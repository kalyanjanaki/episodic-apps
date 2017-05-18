package com.example.episodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by trainer20 on 5/18/17.
 */
@Entity(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long showId;
    private Long seasonNumber;
    private Long episodeNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Long getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Long seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Long getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Long episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    @Transient
    @JsonProperty("title")
    private String getTitle(){
        return "S" + this.seasonNumber + " " + "E" + this.episodeNumber;
    }
}


