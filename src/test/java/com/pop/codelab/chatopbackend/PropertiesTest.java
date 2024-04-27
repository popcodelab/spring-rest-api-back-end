package com.pop.codelab.chatopbackend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(value = "classpath:application-test.properties")
//@ActiveProfiles("test")
//@TestPropertySource(locations = "classpath:application-test.properties")
public class PropertiesTest {

    @Value("${app.name}")
    private String appName;

    @Value("${app.environment}")
    private String appEnvironment;

    @Value("${logging.level.root}")
    private String loggingLevel;

    @Value("${jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Test
    public void testProperties() {
        assertEquals("chatop-test", appName);
        assertEquals("test", appEnvironment);
        assertEquals("INFO", loggingLevel);
        assertEquals("INFO", ddlAuto);
    }


}
