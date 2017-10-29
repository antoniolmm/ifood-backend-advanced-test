package com.ifood.antonio.advancedtest.music;

import java.util.List;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

/**
 * A simplified version of Spotify's Suggestion API containing only the tracks
 * names.
 * 
 * @author antoniomoreira
 */
final class SpotifyMusicSuggestionReponse {

	private List<MusicTrack> tracks;

	@VisibleForTesting
	static SpotifyMusicSuggestionReponse forTracksNames(final List<String> trackNames) {
		final SpotifyMusicSuggestionReponse response = new SpotifyMusicSuggestionReponse();
		response.tracks = Lists.transform(trackNames, trackName -> new MusicTrack(trackName));
		return response;
	}

	public List<MusicTrack> getTracks() {
		return tracks;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("tracks", tracks)
				.toString();
	}

	static class MusicTrack {
		private String name;

		MusicTrack() {
		}

		MusicTrack(final String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this)
					.add("name", name)
					.toString();
		}
	}
}