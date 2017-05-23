package com.example.episodicevents.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by trainer20 on 5/19/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Event.class, name = "Event"),
        @JsonSubTypes.Type(value = PlayEvent.class, name = "play"),
        @JsonSubTypes.Type(value = PauseEvent.class, name = "pause"),
        @JsonSubTypes.Type(value = FastForward.class, name = "fastForward"),
        @JsonSubTypes.Type(value = Rewind.class, name = "rewind"),
        @JsonSubTypes.Type(value = Progress.class, name = "progress"),
        @JsonSubTypes.Type(value = Scrub.class, name = "scrub")
})
public class Event {

    @Id
    private String  id;


    private String type;
    private Long userId;
    private Long showId;
    private Long episodeId;

    @JsonFormat(pattern = "yyyy-MM-DD'T'HH:mm:ss.SSSS")
    private Date createdAt;

}

