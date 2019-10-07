package com.example.demo;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

@RestController
public class ClientController {

    private final RestTemplate restTemplate;

    public ClientController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @RequestMapping(value = "/")
    public String home_v1(@RequestHeader("canary") String flag) throws Exception {
        System.out.println("Canary-Header=" + flag);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("canary", flag);

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        URI url = new URI("http://127.0.0.1:5000/");


        ResponseEntity<String> message = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return "Greetings From API -> " + message;
    }


    @RequestMapping(value = "/test")
    public String test() {

        return "hi";
    }
}


