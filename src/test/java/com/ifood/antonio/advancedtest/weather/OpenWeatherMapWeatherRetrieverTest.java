package com.ifood.antonio.advancedtest.weather;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ifood.antonio.advancedtest.suggestion.CityNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenWeatherMapWeatherRetrieverTest {

	private static final String CITYNAME = "cityname";

	@InjectMocks
	@Resource(name = "open.weather.map.weather.retriever")
	OpenWeatherMapWeatherRetriever subject;

	@Mock
	RestTemplate restTemplate;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldFindTemperatureByCity() {
		when(restTemplate.getForEntity(
				contains(OpenWeatherMapWeatherRetriever.WEATHER_URL),
				any(),
				anyMapOf(String.class, Object.class)))
						.thenReturn(ResponseEntity.ok(OpenWeatherMapResponse.withTemperature(25d)));

		final Double weatherResponse = subject.retrieveCurrentTemperatureByCityName(CITYNAME);
		assertThat(weatherResponse).isEqualTo(25d);
	}

	@Test(expected = CityNotFoundException.class)
	public void shouldThrowExceptionWhenCityNotFound() {
		when(restTemplate.getForEntity(
				contains(OpenWeatherMapWeatherRetriever.WEATHER_URL),
				any(),
				anyMapOf(String.class, Object.class)))
						.thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
		subject.retrieveCurrentTemperatureByCityName(CITYNAME);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenEmptyCityName() {
		subject.retrieveCurrentTemperatureByCityName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenNullCityName() {
		subject.retrieveCurrentTemperatureByCityName(null);
	}

	@Test
	public void shouldFindTemperatureByCoordinates() {
		when(restTemplate.getForEntity(
				contains(OpenWeatherMapWeatherRetriever.WEATHER_URL),
				any(),
				anyMapOf(String.class, Object.class)))
						.thenReturn(ResponseEntity.ok(OpenWeatherMapResponse.withTemperature(25d)));
		final Double weatherResponse = subject.retrieveCurrentTemperatureByCoordinates(10, 50);
		assertThat(weatherResponse).isEqualTo(25d);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenLatitudeIsHigherThan180() {
		subject.retrieveCurrentTemperatureByCoordinates(180.1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenLatitudeIsBelowThanMinus180() {
		subject.retrieveCurrentTemperatureByCoordinates(-180.1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenLongitudeIsHigherThan180() {
		subject.retrieveCurrentTemperatureByCoordinates(0, 180.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenLongitudeIsBelowThanMinus80() {
		subject.retrieveCurrentTemperatureByCoordinates(0, -180.1);
	}
}