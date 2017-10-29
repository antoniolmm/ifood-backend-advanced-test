package com.ifood.antonio.advancedtest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifood.antonio.advancedtest.music.MusicStyle;
import com.ifood.antonio.advancedtest.music.MusicSuggestionRetriever;
import com.ifood.antonio.advancedtest.music.MusicTrack;
import com.ifood.antonio.advancedtest.weather.WeatherRetriever;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvancedTestApplicationTest {

	@Resource(name = "open.weather.map.weather.retriever")
	private WeatherRetriever weatherRetriver;

	@Resource(name = "spotify.music.suggestion.retriever")
	private MusicSuggestionRetriever musicSuggestionRetriever;

	@Test
	public void contextLoads() {
		// weatherRetriver.retrieveCurrentTemperatureByCityName("a");
		final List<MusicTrack> response = musicSuggestionRetriever.suggestTracksByMusicStyle(MusicStyle.ROCK);
	}
}