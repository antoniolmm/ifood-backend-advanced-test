package com.ifood.antonio.advancedtest.suggestion;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

/**
 * Obtains the right MusicStyleByTemperatureRange according to
 * MusicStyleByTemperatureRange's documentation.
 * 
 * @author antoniomoreira
 */
@Component
final class MusicStyleByTemperatureRangeSorter {

	private final List<MusicStyleByTemperatureRange> ranges;

	public MusicStyleByTemperatureRangeSorter() {
		final List<MusicStyleByTemperatureRange> ranges = Lists.newArrayList(MusicStyleByTemperatureRange.values());
		Collections.sort(ranges, MusicStyleByTemperatureRangeComparator.INSTANCE);
		this.ranges = ranges;
	}

	MusicStyleByTemperatureRange getRangeByTemperature(final double temperature) {
		for (final MusicStyleByTemperatureRange range : ranges) {
			if (temperature <= range.getHighestTemperature()) {
				return range;
			}
		}
		throw new IllegalStateException("Could not find a range for " + temperature);
	}

	private static class MusicStyleByTemperatureRangeComparator implements Comparator<MusicStyleByTemperatureRange> {
		private static final MusicStyleByTemperatureRangeComparator INSTANCE = new MusicStyleByTemperatureRangeComparator();

		@Override
		public int compare(final MusicStyleByTemperatureRange o1, final MusicStyleByTemperatureRange o2) {
			return Double.compare(o1.getHighestTemperature(), o2.getHighestTemperature());
		}
	}
}