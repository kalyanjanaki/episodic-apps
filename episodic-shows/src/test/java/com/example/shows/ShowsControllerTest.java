package com.example.shows;

import com.example.episodes.EpisodeRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.CoreMatchers.instanceOf;
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
public class ShowsControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    EpisodeRepository episodeRepository;

    @Test
    public void testCreateShows() throws Exception {
        com.google.gson.JsonObject body = new JsonObject();
        body.addProperty("name","friends");
        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(body);

        MockHttpServletRequestBuilder request = post("/shows").contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);
        this.mvc.perform(request).andExpect(status().isOk()).
                andExpect( jsonPath("$.id", instanceOf(Number.class) ));

    }

    @Test
    public void getShows() throws Exception{

        MockHttpServletRequestBuilder request = get("/shows").contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request).andExpect(status().isOk());
    }
}
