package com.ifood.antonio.advancedtest.weather;

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
	double retrieveCurrentTemperatureByCityName(String cityName);
}