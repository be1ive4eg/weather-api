package com.be1ive.weather.owm.api.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Nikolay on 14.02.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class LocationMixin extends OpenWeatherMapObjectMixin {

    @JsonCreator
    LocationMixin(
            @JsonProperty("lat") Double latitude,
            @JsonProperty("lon") Double longitude) {}

}