package com.example.episodes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer20 on 5/18/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EpisodesControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    EpisodeRepository episodeRepository;

    @Test
    public void CreateEpisode() throws Exception{
        com.google.gson.JsonObject body = new JsonObject();
        body.addProperty("seasonNumber",1);
        body.addProperty("episodeNumber",2);
        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(body);

        MockHttpServletRequestBuilder request = post("/shows/1/episodes").contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);
        this.mvc.perform(request).andExpect(status().isOk()).
                andExpect(jsonPath("$.id", instanceOf(Number.class) )).
                andExpect(jsonPath("$.title",is("S1 E2") )).
                andExpect(jsonPath("$.showId").doesNotExist());
    }

    @Test
    @Transactional
    @Rollback
    public void getEpisodes() throws Exception{

        Episode episode = new Episode();
        episode.setShowId((long) 1);
        episode.setEpisodeNumber((long) 12);
        episode.setSeasonNumber((long) 3);
        episodeRepository.save(episode);

        MockHttpServletRequestBuilder getrequest = get("/shows/1/episodes").contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(getrequest).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title",is("S3 E12") ));
    }

}
