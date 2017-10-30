package com.ifood.antonio.advancedtest.suggestion;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicSuggestionControllerTest {

	@Resource
	MusicSuggestionController controller;

	@Test
	public void shouldWorkWhenLookingByValidCityName() {
		final ResponseEntity<MusicSuggestionResponse> reponse = controller.suggestMusicsByCityName("sao paulo");
		assertThat(reponse.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test(expected = CityNotFoundException.class)
	public void shouldThrowExceptionWhenLookingByInvalidCityName() {
		controller.suggestMusicsByCityName("x");
	}

	@Test
	public void shouldWorkWhenLookingByValidCoordinates() {
		final ResponseEntity<MusicSuggestionResponse> reponse = controller.suggestMusicsByCoordinate(-30, -25);
		assertThat(reponse.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test(expected = InvalidCoordinatesException.class)
	public void shouldThrowExceptionWhenLatitudeIsHigherThan180() {
		controller.suggestMusicsByCoordinate(181, -25);
	}

	@Test(expected = InvalidCoordinatesException.class)
	public void shouldThrowExceptionWhenLatitudeIsBelow180() {
		controller.suggestMusicsByCoordinate(-181, -25);
	}

	@Test(expected = InvalidCoordinatesException.class)
	public void shouldThrowExceptionWhenLongitudeIsHigherThan180() {
		controller.suggestMusicsByCoordinate(25, 181);
	}

	@Test(expected = InvalidCoordinatesException.class)
	public void shouldThrowExceptionWhenLongitudeIsBelow180() {
		controller.suggestMusicsByCoordinate(25, -181);
	}
}
