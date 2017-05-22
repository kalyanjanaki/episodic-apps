package com.example.episodicevents;

import ch.qos.logback.core.util.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer20 on 5/22/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventsControllerTest {

    @Autowired
    MockMvc mvc;

    public String getJosn(String filepath) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(filepath));
        return new String (encoded);
    }

    @Test
    public void testCreateEvent() throws Exception{

        MockHttpServletRequestBuilder request = post("/").contentType(MediaType.APPLICATION_JSON)
                            .content(getJosn("src/test/resources/event.json"));

        this.mvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.userId", equalTo(52) ));
    }

    @Test
    public void testEventRecent() throws Exception {

        MockHttpServletRequestBuilder request = get("/recent").contentType(MediaType.APPLICATION_JSON);
        this.mvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.length()",lessThanOrEqualTo(20)));


    }


}
