package com.example.users;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer20 on 5/17/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTransactionalTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback
    public void testUserCreate() throws Exception{

        com.google.gson.JsonObject body = new JsonObject();
        body.addProperty("email","joe@example.com");
        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(body);

        MockHttpServletRequestBuilder request = post("/users").contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);
        this.mvc.perform(request).andExpect(status().isOk()).
                andExpect( jsonPath("$.id", instanceOf(Number.class) ));
    }
}




