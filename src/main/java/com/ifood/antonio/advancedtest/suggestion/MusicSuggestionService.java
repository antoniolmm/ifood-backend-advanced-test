package com.ifood.antonio.advancedtest.suggestion;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifood.antonio.advancedtest.weather.WeatherRetriever;

@Service
final class MusicSuggestionService {

	private final WeatherRetriever weatherRetriever;

	@Autowired
	public MusicSuggestionService(final WeatherRetriever weatherRetriever) {
		this.weatherRetriever = weatherRetriever;
	}

	MusicSuggestionResponse suggestMusicsByCityName(final String cityName) {
		final double currentTemperature = weatherRetriever.retrieveCurrentTemperatureByCityName(cityName);
		return new MusicSuggestionResponse(currentTemperature, Arrays.asList());
	}
}