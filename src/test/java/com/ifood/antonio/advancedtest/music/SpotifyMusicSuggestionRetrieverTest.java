package com.ifood.antonio.advancedtest.music;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.ifood.antonio.advancedtest.music.SpotifyMusicSuggestionRetriever.AccessRequestResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpotifyMusicSuggestionRetrieverTest {

	@Resource(name = "spotify.music.suggestion.retriever")
	@InjectMocks
	MusicSuggestionRetriever subject;

	@Mock
	RestTemplate restTemplate;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnTrackListWhenNotNullGenre() {
		final List<String> tracks = Arrays.asList("track1", "track2", "track3");
		mockSuccessfulApiCallForMusicTracks(tracks);
		mockAuthorizationApi();

		final List<MusicTrack> response = subject.suggestTracksByMusicStyle(MusicStyle.CLASSICAL);
		assertThat(Lists.transform(response, track -> track.getMusicName())).containsExactlyElementsOf(tracks);
	}

	private void mockAuthorizationApi() {
		when(restTemplate.postForEntity(
				eq(SpotifyMusicSuggestionRetriever.AUTHORIZATION_URL),
				any(HttpEntity.class),
				eq(AccessRequestResponse.class)))
						.thenReturn(ResponseEntity.ok(AccessRequestResponse.withToken("token")));
	}

	private void mockSuccessfulApiCallForMusicTracks(final List<String> tracks) {
		when(restTemplate.exchange(
				contains(SpotifyMusicSuggestionRetriever.RECOMMENDATIONS_URL),
				eq(HttpMethod.GET),
				any(),
				eq(SpotifyMusicSuggestionReponse.class),
				eq(ImmutableMap.of("genre_name", MusicStyle.CLASSICAL.getStyleName()))))
						.thenReturn(ResponseEntity.ok(SpotifyMusicSuggestionReponse.forTracksNames(tracks)));
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionWhenReceiveNullMusicStyle() {
		subject.suggestTracksByMusicStyle(null);
	}
}