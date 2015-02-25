/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 the original author or authors.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.belive.weather.owm.api.impl;

import com.belive.weather.owm.api.City;
import com.belive.weather.owm.api.CurrentCityConditions;
import com.belive.weather.owm.api.CurrentConditions;
import com.belive.weather.owm.api.CurrentConditionsOperations;
import com.belive.weather.owm.api.OpenWeatherMapApi;
import com.belive.weather.owm.api.ParametrisedList;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


/**
 * @author Nikolay Denisenko
 * @version 2015/02/16
 */
public class CurrentConditionsOperationsTemplate implements CurrentConditionsOperations {

    protected final OpenWeatherMapApi api;

    protected final RestTemplate restTemplate;

    public CurrentConditionsOperationsTemplate(OpenWeatherMapApi api, RestTemplate restTemplate) {
        this.api = api;
        this.restTemplate = restTemplate;
    }

    @Override
    public CurrentConditions<City> conditionsNearCityByCityName(String city) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("q", city);
        return api.fetchObject("weather", TypeFactory.defaultInstance().constructType(CurrentCityConditions.class), queryParams);
    }

    @Override
    public CurrentConditions<City> conditionsNearCityByCityAndCountryCode(String city, String country) {
        return conditionsNearCityByCityName(city + "," + country);
    }

    @Override
    public CurrentConditions<City> conditionsNearCityByCityId(String id) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("id", id);
        return api.fetchObject("weather", TypeFactory.defaultInstance().constructType(CurrentCityConditions.class), queryParams);
    }

    @Override
    public ParametrisedList<CurrentConditions<City>> conditionsNearCityByCityIds(String... ids) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        StringBuilder sb = new StringBuilder("");
        for (String id : ids) {
            sb.append(",").append(id);
        }
        sb.deleteCharAt(0);
        queryParams.add("id", sb.toString());

        return api.fetchObjects("group", TypeFactory.defaultInstance().constructType(CurrentCityConditions.class), queryParams);
    }

    @Override
    public CurrentConditions<City> conditionsNearCityByLatLon(double lat, double lon) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("lat", Double.toString(lat));
        queryParams.set("lon", Double.toString(lon));
        return api.fetchObject("weather", TypeFactory.defaultInstance().constructType(CurrentCityConditions.class), queryParams);
    }

    @Override
    public ParametrisedList<CurrentConditions<City>> conditionsNearCityInBox(double topLeftLat, double topLeftLon,
            double botRightLat, double botRightLon) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("bbox", Double.toString(topLeftLat) + "," + Double.toString(topLeftLon) + "," + Double.toString(botRightLat) + "," + Double.toString(botRightLon));
        return api.fetchObjects("box/city", TypeFactory.defaultInstance().constructType(CurrentCityConditions.class), queryParams);
    }

    @Override
    public ParametrisedList<CurrentConditions<City>> conditionsNearCityInCircle(double centrLat, double centrLon, int cnt) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("lat", Double.toString(centrLat));
        queryParams.set("lon", Double.toString(centrLon));
        queryParams.set("count", cnt == 0 ? "1" : Integer.toString(cnt));
        return api.fetchObjects("find", TypeFactory.defaultInstance().constructType(CurrentCityConditions.class), queryParams);
    }
}
