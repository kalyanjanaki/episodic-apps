package com.example.viewings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by trainer20 on 5/22/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventMessage {

    private Long userId;
    private Long  episodeId;
    private Date createdAt;
    int offset;

}
