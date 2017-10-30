package com.ifood.antonio.advancedtest.suggestion;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;
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

	static MusicStyleByTemperatureRange getByTempererature(final double temperature) {
		final List<MusicStyleByTemperatureRange> ranges = Lists.newArrayList(MusicStyleByTemperatureRange.values());
		Collections.sort(ranges, MusicStyleByTemperatureRangeComparator.INSTANCE);
		for (final MusicStyleByTemperatureRange range : ranges) {
			if (temperature <= range.highestTemperature) {
				return range;
			}
		}
		throw new IllegalStateException("Could not find a range for " + temperature);
	}

	private static class MusicStyleByTemperatureRangeComparator implements Comparator<MusicStyleByTemperatureRange> {
		private static final MusicStyleByTemperatureRangeComparator INSTANCE = new MusicStyleByTemperatureRangeComparator();

		@Override
		public int compare(final MusicStyleByTemperatureRange o1, final MusicStyleByTemperatureRange o2) {
			return Double.compare(o1.highestTemperature, o2.highestTemperature);
		}
	}
}