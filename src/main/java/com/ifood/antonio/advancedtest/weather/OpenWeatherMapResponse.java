package com.ifood.antonio.advancedtest.weather;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;

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

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("main", main)
				.toString();
	}

	final static class MainInformations {
		private double temp;

		public double getTemp() {
			return temp;
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this)
					.add("temp", temp)
					.toString();
		}
	}
}