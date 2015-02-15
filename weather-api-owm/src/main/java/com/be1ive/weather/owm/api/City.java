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

package com.be1ive.weather.owm.api;

/**
 * @author Nikolay Denisenko
 * @version 2015/02/16
 */
public class City extends OpenWeatherMapObject {

    private final String id;
    private final String name;
    private final String country;
    private final Location location;

    public City(String id, String name, String country, Location location) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.location = location;
    }

    /**
     * 	City identification
     */
    public String getId() {
        return id;
    }

    /**
     * 	City name
     */
    public String getName() {
        return name;
    }

    /**
     * 	City country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 	City Geo location
     */
    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "CityObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", location=" + location +
                '}';
    }
}
