package com.ifood.antonio.advancedtest.weather;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;

/**
 * WeatherRetriever that search weather information on OpenWeatherMap.
 * 
 * @see <a href="https://openweathermap.org">https://openweathermap.org</a>
 * 
 * @author antoniomoreira
 */
@Component("open.weather.map.weather.retriever")
final class OpenWeatherMapWeatherRetriever implements WeatherRetriever {

	@Resource
	private RestTemplate restTemplate;
	@Value("${openweathermap.api.key}")
	private String apiKey;

	@Override
	public Optional<Double> retrieveCurrentTemperatureByCityName(final String cityName) {
		checkArgument(!Strings.isNullOrEmpty(cityName));
		try {
			final ResponseEntity<OpenWeatherMapResponse> weatherDataResponse = restTemplate.getForEntity(
					"http://api.openweathermap.org/data/2.5/weather?units=metric&q={cityName}&APPID={apiKey}",
					OpenWeatherMapResponse.class,
					ImmutableMap.of(
							"cityName", cityName,
							"apiKey", apiKey));
			if (weatherDataResponse.getStatusCode() != HttpStatus.OK) {
				return Optional.empty();
			}
			return Optional.of(weatherDataResponse.getBody().getMain().getTemp());
		} catch (final HttpClientErrorException e) {
			throw handleClientException(e, cityName);
		}
	}

	private static RuntimeException handleClientException(final HttpClientErrorException e, final String cityName) {
		if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
			return new CityNotFoundException(cityName);
		}
		return e;
	}
}