package com.pop.codelab.chatopbackend;


import com.pop.codelab.chatopbackend.message.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test
@TestPropertySource(value = "classpath:application-test.properties")
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MessageService messageService;

    @Value("${jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Test
    public void testProperties() {
        assertEquals("validate", ddlAuto);
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(messageService).isNotNull();
    }

    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        mockMvc.perform(get("/api/welcome").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testPublicEndpointAccessAuthentication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login"))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Or HttpStatus.FORBIDDEN if configured to return 403
    }

}
