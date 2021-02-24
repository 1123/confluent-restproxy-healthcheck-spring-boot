package io.confluent.samples.restproxy.healthcheck.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class DemoApplicationTests {

	@Test
	void testGetTopicsFromRestProxy() {
		RestProxyTestService restProxyTestService = new RestProxyTestService();
		restProxyTestService.testGetTopics();
	}

	@Test
	void testProduceViaRestProxy() {
		RestProxyTestService restProxyTestService = new RestProxyTestService();
		restProxyTestService.testProduce();
	}

}

