package com.ifood.antonio.advancedtest.weather;

import com.google.common.annotations.VisibleForTesting;

/**
 * Simple response POJO for OpenWeatherMap.<br>
 * Contains only current temperature.
 * 
 * @author antoniomoreira
 */
final class OpenWeatherMapResponse {

	private MainInformations main;

	@VisibleForTesting
	static OpenWeatherMapResponse withTemperature(final double temperature) {
		final OpenWeatherMapResponse response = new OpenWeatherMapResponse();
		response.main = new MainInformations();
		response.main.temp = temperature;
		return response;
	}

	public MainInformations getMain() {
		return main;
	}

	final static class MainInformations {
		private double temp;

		public double getTemp() {
			return temp;
		}
	}
}
