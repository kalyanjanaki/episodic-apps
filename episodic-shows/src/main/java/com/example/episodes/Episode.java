package com.example.episodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by trainer20 on 5/18/17.
 */
@Entity(name = "episodes")
@Getter
@Setter
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long showId;
    private Long seasonNumber;
    private Long episodeNumber;


    @JsonIgnore
    public Long getShowId() {
        return showId;
    }



    @Transient
    @JsonProperty("title")
    private String getTitle(){
        return "S" + this.seasonNumber + " " + "E" + this.episodeNumber;
    }
}


