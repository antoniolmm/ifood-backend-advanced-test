# iFood Backend Advanced Test

Create a micro-service able to accept RESTful requests receiving as parameter either city name or lat long coordinates and returns a playlist (only track names is fine) suggestion according to the current temperature.

## Business rules

* If temperature (celcius) is above 30 degrees, suggest tracks for party
* In case temperature is above 15 and below 30 degrees, suggest pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
* Otherwise, if it's freezing outside, suggests classical music tracks 

## Hints

You can make usage of OpenWeatherMaps API (https://openweathermap.org) to fetch temperature data and Spotify (https://developer.spotify.com) to suggest the tracks as part of the playlist.

## Non functional requirements

As this service will be a worldwide success,it must be prepared to be fault tolerant,responsive and resilient.

Use whatever language, tools and frameworks you feel comfortable to. 

Also, briefly elaborate on your solution, architecture details, choice of patterns and frameworks.

Fork this repository and submit your code.

 # Solution

## General

The solution was made in Java 8 with the Eclipse IDE.
Almost all the frameworks were provided by spring, being the most important Spring-Boot and Spring Web MVC.
Maven was used as dependency management and build tool.
As suggested, the weather information is provided by Open Weather Map and music suggestions is provided by Spotify.

## Architectural details

The micro-service was elaborated in 3 layers:
* Controller: Incoming requests mapping.
* Service: Bussiness logic and basic validation.
* Retriever: External services information retrieving.

## Instructions

In order to get the service runing, use the spring-boot maven plugin: just type `mvn spring-boot:run`. The server with be up on port 8080.

Two services are going to be exposed:
* Search for city name;
  * Sample request  `http://localhost:8080/suggestion/city-name/sydney`.
  * If a city cannot be found, a `BAD REQUEST` (400) with be returned with an error message.
* Search for geographic coordinates.
  * Sample request  `http://localhost:8080/suggestion/lat/-26.6/lon/-27`.
  * If the coordinates values are invalid (higher than 180 or below -180), a `BAD REQUEST` (400) with be retuning with an error message containing the invalid value.

Both services return the temperature of the found location, the music style associated and a list of track names.
