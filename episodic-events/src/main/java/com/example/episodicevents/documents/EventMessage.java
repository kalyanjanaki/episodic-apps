package com.example.episodicevents.documents;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by trainer20 on 5/23/17.
 */
public class EventMessage {

    private Long userId;
    private Long episodeId;
    private Date createdAt;
    private int offset;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    @JsonFormat(pattern = "yyyy-MM-DD'T'HH:mm:ss.SSSS")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
