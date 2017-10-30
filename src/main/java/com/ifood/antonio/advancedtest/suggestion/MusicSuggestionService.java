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

	@Autowired
	public MusicSuggestionService(
			final WeatherRetriever weatherRetriever,
			final MusicSuggestionRetriever musicSuggestionRetriever) {
		this.weatherRetriever = weatherRetriever;
		this.musicSuggestionRetriever = musicSuggestionRetriever;
	}

	MusicSuggestionResponse suggestMusicsByCityName(final String cityName) {
		final double currentTemperature = weatherRetriever.retrieveCurrentTemperatureByCityName(cityName);
		final MusicStyle appropriatedMusicStyle = MusicStyleByTemperatureRange
				.getByTempererature(currentTemperature).getMusicStyle();
		final List<MusicTrack> musicTracks = musicSuggestionRetriever
				.suggestTracksByMusicStyle(appropriatedMusicStyle);
		return new MusicSuggestionResponse(currentTemperature, appropriatedMusicStyle, musicTracks);
	}
}