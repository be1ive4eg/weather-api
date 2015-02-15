package com.be1ive.weather.owm.api.impl.json;

import com.be1ive.weather.owm.api.LocationObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

/**
 * Created by Nikolay on 15.02.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CityObjectMixin extends OpenWeatherObjectMixin {

    @JsonCreator
    CityObjectMixin(
            @JsonProperty("id") String id,
            @JsonDeserialize(using = UnixDateDeserializer.class) @JsonProperty("dt") Date dt,
            @JsonProperty("name") String name,
            @JsonProperty("coord") LocationObject coord) {}
}