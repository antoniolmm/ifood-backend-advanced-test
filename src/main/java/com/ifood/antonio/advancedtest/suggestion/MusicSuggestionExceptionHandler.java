package com.ifood.antonio.advancedtest.suggestion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MusicSuggestionExceptionHandler {

	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<String> handleCityNotFoundException(final CityNotFoundException e) {
		return ResponseEntity.badRequest().body("Could not found city " + e.getCityName());
	}

	@ExceptionHandler(InvalidCoordinatesException.class)
	public ResponseEntity<String> handleInvalidCoordinatesException(final InvalidCoordinatesException e) {
		return ResponseEntity.badRequest().body("Invalid coordinate value " + e.getInvalidCoordinate());
	}
}