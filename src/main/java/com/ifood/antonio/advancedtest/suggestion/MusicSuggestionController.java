package com.ifood.antonio.advancedtest.suggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Externalize services for music suggestion based on a city's current
 * temperature.
 * <p>
 * The rules for music style based on the city's temperature are the following:
 * <ul>
 * <li>For temperatures higher than 30 degrees celsius, <b>party</b> tracks are
 * suggested;
 * <li>For temperatures higher than 15 and below or equal to 30 degrees celsius,
 * <b>pop</b> tracks are suggested;
 * <li>For temperatures higher than 10 and below or equal to 15 degrees celsius,
 * <b>rock</b> tracks are suggested;
 * <li>For temperatures below or equal to 10 degrees celsius, <b>classical</b>
 * tracks are suggested.
 * </ul>
 * <p>
 * A <b>400</b> status is returned when a city can not be found.
 * 
 * @author antoniomoreira
 */
@RestController
@RequestMapping(path = "suggestion")
final class MusicSuggestionController {

	private final MusicSuggestionService musicSuggestionService;

	@Autowired
	public MusicSuggestionController(final MusicSuggestionService musicSuggestionService) {
		this.musicSuggestionService = musicSuggestionService;
	}

	@RequestMapping(path = "city_name/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	ResponseEntity<MusicSuggestionResponse> suggestMusicsByCityName(
			@PathVariable(name = "cityName")
			final String cityName) {
		return ResponseEntity.ok(musicSuggestionService.suggestMusicsByCityName(cityName));
	}
}