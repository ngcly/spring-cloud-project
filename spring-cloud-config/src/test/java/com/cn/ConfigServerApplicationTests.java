package com.cn;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author chenning
 */
@SpringBootTest
public class ConfigServerApplicationTests {
    @Value("${server.port}")
    private int port = 8001;

    @Test
    public void configurationAvailable() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = new TestRestTemplate()
                .withBasicAuth("ngcly", "123456")
                .getForEntity("http://localhost:" + port + "/application-config/dev", Map.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
}
