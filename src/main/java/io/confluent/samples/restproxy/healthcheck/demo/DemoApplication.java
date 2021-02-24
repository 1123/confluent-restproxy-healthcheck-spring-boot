package io.confluent.samples.restproxy.healthcheck.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	@Autowired
	private RestProxyTestService restProxyTestService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Scheduled(fixedDelay = 10000)
	public void testRestProxy() {
		restProxyTestService.testGetTopics();
		restProxyTestService.testProduce();
	}

}


