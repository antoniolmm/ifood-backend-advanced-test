package com.ifood.antonio.advancedtest.music;

import static org.assertj.core.util.Preconditions.checkNotNull;

import java.util.Base64;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

/**
 * A MusicSuggestionRetriever implementation based on Spotify's Recommendations
 * API.
 * 
 * @see <a href=
 *      "https://developer.spotify.com/web-api/get-recommendations/">Spotify's
 *      Recommendations API</a>
 * @author antoniomoreira
 */
@Service("spotify.music.suggestion.retriever")
public class SpotifyMusicSuggestionRetriever implements MusicSuggestionRetriever {

	@VisibleForTesting
	static final String AUTHORIZATION_URL = "https://accounts.spotify.com/api/token";
	@VisibleForTesting
	static final String RECOMMENDATIONS_URL = "https://api.spotify.com/v1/recommendations";

	@Resource
	private RestTemplate restTemplate;
	@Value("${spotify.client.id}")
	private String clientId;
	@Value("${spotify.client.secret}")
	private String clientSecret;

	@Override
	public List<MusicTrack> suggestTracksByMusicStyle(final MusicStyle musicStyle) {
		checkNotNull(musicStyle);

		final String accessToken = requestAccessToken();

		final HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);

		final SpotifyMusicSuggestionReponse reponse = restTemplate.exchange(
				RECOMMENDATIONS_URL + "?seed_genres={genre_name}",
				HttpMethod.GET,
				new HttpEntity<>(headers),
				SpotifyMusicSuggestionReponse.class,
				ImmutableMap.of("genre_name", musicStyle.getStyleName())).getBody();

		return Lists.transform(reponse.getTracks(), responseTrack -> new MusicTrack(responseTrack.getName()));
	}

	private String requestAccessToken() {
		return restTemplate.postForEntity(
				AUTHORIZATION_URL,
				buildAccessRequestHttpEntity(),
				AccessRequestResponse.class).getBody().token;
	}

	private HttpEntity<?> buildAccessRequestHttpEntity() {
		final String authorization = "Basic "
				+ Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);

		final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "client_credentials");

		return new HttpEntity<>(body, headers);
	}

	@VisibleForTesting
	static class AccessRequestResponse {
		@JsonProperty("access_token")
		private String token;

		static AccessRequestResponse withToken(final String token) {
			final AccessRequestResponse response = new AccessRequestResponse();
			response.token = token;
			return response;
		}
	}
}