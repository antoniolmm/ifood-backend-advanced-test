package com.ifood.antonio.advancedtest.weather;

import java.util.Optional;

/**
 * Retrieve weather information for locations.
 * 
 * @author antoniomoreira
 */
public interface WeatherRetriever {

	/**
	 * Retrieve current temperature in a city by the city's name.
	 * 
	 * @param cityName
	 *            the name of the city. Not null
	 * @return the current temperature in celsius
	 */
	Optional<Double> retrieveCurrentTemperatureByCityName(String cityName);
}