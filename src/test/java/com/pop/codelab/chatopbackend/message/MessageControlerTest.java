package com.pop.codelab.chatopbackend.message;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pop.codelab.chatopbackend.JsonUtil;
import com.pop.codelab.chatopbackend.business.message.Message;
import com.pop.codelab.chatopbackend.business.message.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test
public class MessageControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageService messageService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(messageService).isNotNull();
    }

    @Test
    public void getAllMessagesAPI() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/messages/")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    public void findOne() throws Exception {
        mockMvc.perform(get("/api/messages/1"))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void createMessageAPI() throws Exception {
        String messageContent = "Unit test message";
        Message message = Message.builder()
                .message(messageContent)
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/messages/")
                        .content(JsonUtil.toJson(message))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(messageContent));
    }
}
