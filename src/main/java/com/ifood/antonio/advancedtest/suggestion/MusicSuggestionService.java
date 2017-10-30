package com.ifood.antonio.advancedtest.suggestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifood.antonio.advancedtest.music.MusicStyle;
import com.ifood.antonio.advancedtest.music.MusicSuggestionRetriever;
import com.ifood.antonio.advancedtest.music.MusicTrack;
import com.ifood.antonio.advancedtest.weather.WeatherRetriever;

@Service
final class MusicSuggestionService {

	private final WeatherRetriever weatherRetriever;
	private final MusicSuggestionRetriever musicSuggestionRetriever;
	private final MusicStyleByTemperatureRangeSorter rangeSorter;

	@Autowired
	public MusicSuggestionService(
			final WeatherRetriever weatherRetriever,
			final MusicSuggestionRetriever musicSuggestionRetriever,
			final MusicStyleByTemperatureRangeSorter rangeSorter) {
		this.weatherRetriever = weatherRetriever;
		this.musicSuggestionRetriever = musicSuggestionRetriever;
		this.rangeSorter = rangeSorter;
	}

	MusicSuggestionResponse suggestMusicsByCityName(final String cityName) {
		return suggestMusicByTemperature(weatherRetriever.retrieveCurrentTemperatureByCityName(cityName));
	}

	MusicSuggestionResponse suggestMusicsByCoordinates(final double latitude, final double longitude) {
		validateCoordinates(latitude, longitude);
		return suggestMusicByTemperature(weatherRetriever.retrieveCurrentTemperatureByCoordinates(latitude, longitude));
	}

	private MusicSuggestionResponse suggestMusicByTemperature(final double currentTemperature) {
		final MusicStyle appropriatedMusicStyle = rangeSorter.getRangeByTemperature(currentTemperature).getMusicStyle();
		final List<MusicTrack> musicTracks = musicSuggestionRetriever
				.suggestTracksByMusicStyle(appropriatedMusicStyle);
		return new MusicSuggestionResponse(currentTemperature, appropriatedMusicStyle, musicTracks);
	}

	private static void validateCoordinates(final double latitude, final double longitude) {
		if (latitude > 180 || latitude < -180) {
			throw new InvalidCoordinatesException(latitude);
		}
		if (longitude > 180 || longitude < -180) {
			throw new InvalidCoordinatesException(longitude);
		}
	}
}