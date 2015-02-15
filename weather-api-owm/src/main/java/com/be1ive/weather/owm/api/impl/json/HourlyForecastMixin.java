package com.be1ive.weather.owm.api.impl.json;

import com.be1ive.weather.owm.api.City;
import com.be1ive.weather.owm.api.CurrentWeather;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Nikolay on 15.02.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class HourlyForecastMixin extends OpenWeatherMapObjectMixin {

    @JsonProperty("city")
    City city;

    @JsonProperty("list")
    List<CurrentWeather> currentWeather;
}