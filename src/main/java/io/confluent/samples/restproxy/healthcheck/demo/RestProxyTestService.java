package io.confluent.samples.restproxy.healthcheck.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

// TODO: also check consumption of data.

@Slf4j
@Service
public class RestProxyTestService {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpEntity<String> entity;

    public RestProxyTestService() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/vnd.kafka.v2+json");
        entity = new HttpEntity<>(null, headers);
    }

    public void testGetTopics() {
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8082/topics", HttpMethod.GET, entity, String.class);
        log.info(result.toString());
    }

    public void testProduce() {
        HttpHeaders produceHeaders = new HttpHeaders();
        produceHeaders.add("Accept", "application/vnd.kafka.v2+json");
        produceHeaders.add("Content-Type", "application/vnd.kafka.json.v2+json");
        HttpEntity<String> produceEntity = new HttpEntity<>("{\"records\":[{\"value\":{\"foo\":\"bar\"}}]}", produceHeaders);
        // TODO: to configure a keystore / truststore, this may help: https://medium.com/@itzgeoff/using-a-custom-trust-store-with-resttemplate-in-spring-boot-77b18f6a5c39
        // TODO: make rest-proxy url configurable.
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8082/topics/test-rest-proxy-produce", HttpMethod.POST, produceEntity, String.class);
        log.info(result.toString());
    }

}
