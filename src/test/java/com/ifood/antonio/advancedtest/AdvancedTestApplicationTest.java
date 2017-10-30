package com.ifood.antonio.advancedtest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.ImmutableMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvancedTestApplicationTest {

	private static final String BASE_URL = "http://localhost:8080/suggestion";

	@Resource
	private RestTemplate restTemplate;

	@Test
	public void shouldWorkWhenLookingByValidCityName() {
		final ResponseEntity<String> reponse = restTemplate.getForEntity(
				BASE_URL + "/city-name/{cityName}",
				String.class,
				ImmutableMap.of("cityName", "sao paulo"));

		assertThat(reponse.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}