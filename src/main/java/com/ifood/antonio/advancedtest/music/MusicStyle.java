package com.ifood.antonio.advancedtest.music;

public enum MusicStyle {

	PARTY("party"),
	POP("pop"),
	ROCK("rock"),
	CLASSICAL("classical");

	private final String styleName;

	private MusicStyle(final String styleName) {
		this.styleName = styleName;
	}

	public String getStyleName() {
		return styleName;
	}
}