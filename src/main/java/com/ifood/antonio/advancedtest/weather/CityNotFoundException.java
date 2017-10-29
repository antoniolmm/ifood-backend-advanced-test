package com.ifood.antonio.advancedtest.weather;

public class CityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final String cityName;

	public CityNotFoundException(final String cityName) {
		super();
		this.cityName = cityName;
	}

	public String getCityName() {
		return cityName;
	}

	@Override
	public String getMessage() {
		return "city.not.found";
	}
}