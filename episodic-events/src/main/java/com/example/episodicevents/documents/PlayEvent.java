package com.example.episodicevents.documents;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by trainer20 on 5/22/17.
 */
@Getter
@Setter
public class PlayEvent extends Event {

    private Data data;

    @Setter
    @Getter
    private static class Data{
        private int offset;
    }

}
