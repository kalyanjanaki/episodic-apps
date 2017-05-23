package com.example.viewings;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by trainer20 on 5/19/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ViewingsControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ViewingRepository viewingRepository;

    @Test
    public void testCreateViewing() throws Exception{

        viewingRepository.deleteAll();
        com.google.gson.JsonObject body = new JsonObject();
        body.addProperty("episodeId",5);
        body.addProperty("updatedAt","2017-05-04T11:45:34.9182");
        body.addProperty("timecode",79);
        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(body);

        MockHttpServletRequestBuilder request = patch("/users/1/viewings").contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);

        this.mvc.perform(request).andExpect(status().isOk());

    }

    @Test
    public void testUpdateViewing() throws Exception{


        com.google.gson.JsonObject body = new JsonObject();
        body.addProperty("episodeId",5);
        body.addProperty("updatedAt","2017-05-04T11:45:34.9182");
        body.addProperty("timecode",80);
        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(body);

        MockHttpServletRequestBuilder request = patch("/users/1/viewings").contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);

        this.mvc.perform(request).andExpect(status().isOk());


    }

}
