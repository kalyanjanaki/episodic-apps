package com.example.episodicevents.documents;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by trainer20 on 5/22/17.
 */
@Getter
@Setter
public class Progress extends Event {

    private Data data;


    public static class Data{
        int offset;

        public int getOffset(){
            return offset;
        }

        public void setOffset(int offset){
            this.offset = offset;
        }
    }
}
