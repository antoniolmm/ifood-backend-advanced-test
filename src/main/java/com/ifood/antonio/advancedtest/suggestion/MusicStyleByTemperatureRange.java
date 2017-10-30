package com.ifood.antonio.advancedtest.suggestion;

import static com.google.common.base.Preconditions.checkNotNull;

import com.ifood.antonio.advancedtest.music.MusicStyle;

/**
 * Relates a MusicStyle with a temperature range.<br>
 * Each element lowest temperature is the previous element highest temperature,
 * with a exception to FREEZING which accomplishes anything below 10
 * degrees.<br>
 * 
 * The range is closed to the highest temperature, so 10 degrees is FREEZING,
 * not COLD.
 * 
 * @author antoniomoreira
 */
enum MusicStyleByTemperatureRange {

	HOT(MusicStyle.PARTY, Integer.MAX_VALUE),
	WARM(MusicStyle.POP, 30),
	COLD(MusicStyle.ROCK, 15),
	FREEZING(MusicStyle.CLASSICAL, 10);

	private final MusicStyle musicStyle;
	private final int highestTemperature;

	private MusicStyleByTemperatureRange(
			final MusicStyle musicStyle,
			final int highestTemperature) {
		this.musicStyle = checkNotNull(musicStyle);
		this.highestTemperature = checkNotNull(highestTemperature);
	}

	MusicStyle getMusicStyle() {
		return musicStyle;
	}

	public int getHighestTemperature() {
		return highestTemperature;
	}
}