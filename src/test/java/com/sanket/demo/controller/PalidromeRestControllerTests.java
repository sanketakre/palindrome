package com.sanket.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PalidromeRestControllerTests {
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	void healthCheck() throws Exception {	

		ResponseEntity<String> response = restTemplate.getForEntity(
				new URL("http://localhost:" + port + "/myapp/health").toString(), String.class);
	        assertEquals("Up", response.getBody());	
	}
	
	@Test
	void checkPalindrome() throws Exception {	

		ResponseEntity<Object> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/myapp/userName/sanket/strToTest/saras").toString(), Object.class);
	    assertEquals(true, response.getBody());	
	}
	
}
