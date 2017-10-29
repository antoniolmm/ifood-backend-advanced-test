package com.ifood.antonio.advancedtest.music;

import java.util.List;

/**
 * Provides musics suggestions based on MusicStyle.
 * 
 * @author antoniomoreira
 */
public interface MusicSuggestionRetriever {

	/**
	 * @param MusicStyle
	 *            the style to be used for music suggestion
	 * @return a list of MusicTracks. Never null
	 */
	List<MusicTrack> suggestTracksByMusicStyle(MusicStyle MusicStyle);
}