package com.ifood.antonio.advancedtest.suggestion;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.base.MoreObjects;
import com.ifood.antonio.advancedtest.music.MusicStyle;
import com.ifood.antonio.advancedtest.music.MusicTrack;

final class MusicSuggestionResponse {

	private final double temperature;
	private final MusicStyle musicStyle;
	private final List<MusicTrack> musicTracks;

	public MusicSuggestionResponse(
			final double temperature,
			final MusicStyle musicStyle,
			final List<MusicTrack> musicTracks) {
		this.temperature = checkNotNull(temperature);
		this.musicStyle = musicStyle;
		this.musicTracks = checkNotNull(musicTracks);
	}

	public double getTemperature() {
		return temperature;
	}

	public MusicStyle getMusicStyle() {
		return musicStyle;
	}

	public List<MusicTrack> getMusicTracks() {
		return musicTracks;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("temperature", temperature)
				.add("musicTracks", musicTracks)
				.toString();
	}
}