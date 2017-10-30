package com.ifood.antonio.advancedtest.weather;

import static com.google.common.base.Preconditions.checkArgument;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.ifood.antonio.advancedtest.suggestion.CityNotFoundException;

/**
 * WeatherRetriever that search weather information on OpenWeatherMap.
 * 
 * @see <a href="https://openweathermap.org">https://openweathermap.org</a>
 * 
 * @author antoniomoreira
 */
@Service("open.weather.map.weather.retriever")
final class OpenWeatherMapWeatherRetriever implements WeatherRetriever {

	@VisibleForTesting
	static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";

	@Resource
	private RestTemplate restTemplate;
	@Value("${openweathermap.api.key}")
	private String apiKey;

	@Override
	public double retrieveCurrentTemperatureByCityName(final String cityName) {
		checkArgument(!Strings.isNullOrEmpty(cityName));
		try {
			final ResponseEntity<OpenWeatherMapResponse> weatherDataResponse = restTemplate.getForEntity(
					WEATHER_URL + "?units=metric&q={cityName}&APPID={apiKey}",
					OpenWeatherMapResponse.class,
					ImmutableMap.of(
							"cityName", cityName,
							"apiKey", apiKey));
			return weatherDataResponse.getBody().getMain().getTemp();
		} catch (final HttpClientErrorException e) {
			throw handleClientExceptionForCityNotFound(e, cityName);
		}
	}

	private static RuntimeException handleClientExceptionForCityNotFound(
			final HttpClientErrorException e,
			final String cityName) {
		if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
			return new CityNotFoundException(cityName);
		}
		return e;
	}

	@Override
	public double retrieveCurrentTemperatureByCoordinates(final double latitude, final double longitude) {
		checkCoordinatesValues(latitude, longitude);
		final ResponseEntity<OpenWeatherMapResponse> weatherDataResponse = restTemplate.getForEntity(
				WEATHER_URL + "?units=metric&lat={latitude}&lon={longitude}&APPID={apiKey}",
				OpenWeatherMapResponse.class,
				ImmutableMap.of(
						"latitude", latitude,
						"longitude", longitude,
						"apiKey", apiKey));
		return weatherDataResponse.getBody().getMain().getTemp();
	}

	private static void checkCoordinatesValues(final double latitude, final double longitude) {
		checkArgument(latitude <= 180 && latitude >= -180 && longitude <= 180 && longitude >= -180);
	}
}