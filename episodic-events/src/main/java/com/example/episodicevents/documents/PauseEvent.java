package com.example.episodicevents.documents;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by trainer20 on 5/22/17.
 */
@Getter
@Setter
public class PauseEvent extends Event {

    private Data data;

    @Getter
    @Setter
    private static class Data{
        private int offset;

    }
}
