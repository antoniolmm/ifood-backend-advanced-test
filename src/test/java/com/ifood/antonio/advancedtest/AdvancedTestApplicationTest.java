package com.ifood.antonio.advancedtest;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifood.antonio.advancedtest.weather.WeatherRetriever;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvancedTestApplicationTest {

	@Resource(name = "open.weather.map.weather.retriever")
	private WeatherRetriever weatherRetriver;

	@Test
	public void contextLoads() {
		weatherRetriver.retrieveTemperatureByCityName("a");
	}
}