package com.ifood.antonio.advancedtest.suggestion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CityNotFoundExceptionHandler {

	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<String> handle(final CityNotFoundException e) {
		return ResponseEntity.badRequest().body("Could not found city " + e.getCityName());
	}
}