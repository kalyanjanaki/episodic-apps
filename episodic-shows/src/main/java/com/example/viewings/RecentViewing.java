package com.example.viewings;

import com.example.episodes.Episode;
import com.example.shows.Show;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by trainer20 on 5/18/17.
 */
public class RecentViewing {

    private Show show;
    private Episode episode;
    private Date updatedAt;
    private int timecode;

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    @JsonFormat(pattern = "yyyy-MM-dd 'T' HH:mm:ss")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getTimecode() {
        return timecode;
    }

    public void setTimecode(int timecode) {
        this.timecode = timecode;
    }
}
