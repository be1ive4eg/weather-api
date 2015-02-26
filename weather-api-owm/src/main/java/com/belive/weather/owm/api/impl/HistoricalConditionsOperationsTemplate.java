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

import com.belive.weather.owm.api.*;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


/**
 * @author Nikolay Denisenko
 * @version 2015/02/16
 */
public class HistoricalConditionsOperationsTemplate implements HistoricalConditionsOperations {

    protected final OpenWeatherMapApi api;

    protected final RestTemplate restTemplate;

    public HistoricalConditionsOperationsTemplate(OpenWeatherMapApi api, RestTemplate restTemplate) {
        this.api = api;
        this.restTemplate = restTemplate;
    }

    @Override
    public HistoricalConditions<City> conditionsNearCityByNameInPeriod(String city, int start, int end) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("q", city);
        queryParams.set("start", Integer.toString(start));
        if (start <= end) {
            queryParams.set("end", Integer.toString(end));
        } else {
            queryParams.set("cnt", Integer.toString(1));
        }
        return api.fetchObject("history/city", TypeFactory.defaultInstance().constructType(HistoricalCityConditions.class), queryParams);
    }

    @Override
    public HistoricalConditions<City> conditionsNearCityByNameAndCountryInPeriod(String city, String country,
            int start, int end) {
        return conditionsNearCityByNameInPeriod(city + "," + country, start, end);
    }

    @Override
    public HistoricalConditions<City> conditionsNearCityByIdInPeriod(String id, int start, int end) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("id", id);
        queryParams.set("start", Integer.toString(start));
        if (start <= end) {
            queryParams.set("end", Integer.toString(end));
        } else {
            queryParams.set("cnt", Integer.toString(1));
        }
        queryParams.set("type", "hour");
        return api.fetchObject("history/city", TypeFactory.defaultInstance().constructType(HistoricalCityConditions.class),
                queryParams);
    }

    @Override
    public HistoricalConditions<City> conditionsNearCityByLatLonInPeriod(double lat, double lon, int start, int end) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.set("lat", Double.toString(lat));
        queryParams.set("lon", Double.toString(lon));
        queryParams.set("start", Integer.toString(start));
        if (start <= end) {
            queryParams.set("end", Integer.toString(end));
        } else {
            queryParams.set("cnt", Integer.toString(1));
        }
        queryParams.set("type", "hour");
        return api.fetchObject("history/city", TypeFactory.defaultInstance().constructType(HistoricalCityConditions.class), queryParams);
    }

    @Override
    public HistoricalConditions<City> conditionsNearCityByNameAtTime(String city, int start) {
        return conditionsNearCityByNameInPeriod(city, start, -1);
    }

    @Override
    public HistoricalConditions<City> conditionsNearCityByNameAndCountryAtTime(String city, String country,
            int start) {
        return conditionsNearCityByNameAndCountryInPeriod(city, country, start, -1);
    }

    @Override
    public HistoricalConditions<City> conditionsNearCityByIdAtTime(String id, int start) {
        return conditionsNearCityByIdInPeriod(id, start, -1);
    }

    @Override
    public HistoricalConditions<City> conditionsNearCityByLatLonAtTime(double lat, double lon, int start) {
        return conditionsNearCityByLatLonInPeriod(lat, lon, start, -1);
    }

}
