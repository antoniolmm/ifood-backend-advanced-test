package com.ifood.antonio.advancedtest.suggestion;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MusicStyleByTemperatureRangeTest {

	@Test
	public void shouldBeFreezingWhenTemperatureIsBelow10Degrees() {
		Assertions.assertThat(MusicStyleByTemperatureRange.getByTempererature(9.999))
				.isEqualTo(MusicStyleByTemperatureRange.FREEZING);
	}

	@Test
	public void shouldBeFreezingWhenTemperatureIs10Degrees() {
		Assertions.assertThat(MusicStyleByTemperatureRange.getByTempererature(10))
				.isEqualTo(MusicStyleByTemperatureRange.FREEZING);
	}

	@Test
	public void shouldBeColdWhenTemperatureIsAbove10Degrees() {
		Assertions.assertThat(MusicStyleByTemperatureRange.getByTempererature(10.001))
				.isEqualTo(MusicStyleByTemperatureRange.COLD);
	}

	@Test
	public void shouldBeColdWhenTemperatureIs15Degrees() {
		Assertions.assertThat(MusicStyleByTemperatureRange.getByTempererature(15))
				.isEqualTo(MusicStyleByTemperatureRange.COLD);
	}

	@Test
	public void shouldBeHotWhenTemperatureIs100() {
		Assertions.assertThat(MusicStyleByTemperatureRange.getByTempererature(100))
				.isEqualTo(MusicStyleByTemperatureRange.HOT);
	}
}