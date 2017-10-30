package com.ifood.antonio.advancedtest.suggestion;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MusicStyleByTemperatureRangeSorterTest {

	MusicStyleByTemperatureRangeSorter subject = new MusicStyleByTemperatureRangeSorter();

	@Test
	public void shouldBeFreezingWhenTemperatureIsBelow10Degrees() {
		Assertions.assertThat(subject.getRangeByTemperature(9.999))
				.isEqualTo(MusicStyleByTemperatureRange.FREEZING);
	}

	@Test
	public void shouldBeFreezingWhenTemperatureIs10Degrees() {
		Assertions.assertThat(subject.getRangeByTemperature(10))
				.isEqualTo(MusicStyleByTemperatureRange.FREEZING);
	}

	@Test
	public void shouldBeColdWhenTemperatureIsAbove10Degrees() {
		Assertions.assertThat(subject.getRangeByTemperature(10.001))
				.isEqualTo(MusicStyleByTemperatureRange.COLD);
	}

	@Test
	public void shouldBeColdWhenTemperatureIs15Degrees() {
		Assertions.assertThat(subject.getRangeByTemperature(15))
				.isEqualTo(MusicStyleByTemperatureRange.COLD);
	}

	@Test
	public void shouldBeHotWhenTemperatureIs100() {
		Assertions.assertThat(subject.getRangeByTemperature(100))
				.isEqualTo(MusicStyleByTemperatureRange.HOT);
	}
}