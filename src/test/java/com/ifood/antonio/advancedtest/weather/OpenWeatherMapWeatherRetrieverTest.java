package com.ifood.antonio.advancedtest.weather;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherMapWeatherRetrieverTest {

	private static final String CITYNAME = "cityname";

	@InjectMocks
	OpenWeatherMapWeatherRetriever subject = new OpenWeatherMapWeatherRetriever();

	@Mock
	RestTemplate restTemplate;

	@Test
	public void shouldFindTemperatureByCity() {
		when(restTemplate.getForEntity(contains(CITYNAME), any()))
				.thenReturn(ResponseEntity.ok(OpenWeatherMapResponse.withTemperature(25d)));

		final Optional<Double> weatherResponse = subject.retrieveTemperatureByCityName(CITYNAME);
		assertThat(weatherResponse.get()).isEqualTo(25d);
	}

	@Test(expected = CityNotFoundException.class)
	public void shouldThrowExceptionWhenCityNotFound() {
		when(restTemplate.getForEntity(contains(CITYNAME), any()))
				.thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
		subject.retrieveTemperatureByCityName(CITYNAME);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenEmptyCityName() {
		subject.retrieveTemperatureByCityName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenNullCityName() {
		subject.retrieveTemperatureByCityName(null);
	}
}