package com.be1ive.weather.owm.api;

/**
 * Defines operations for reading weather forecast for 5 days with data every 3 hours
 * @author
 */
public interface HourlyForecastOperations {

    /**
     * Retrieves weather forecast by city name
     * @param city City Name
     * @return the requested {@link HourlyForecast}
     */
    HourlyForecast hourlyForecastByCityName(String city);

    /**
     * Retrieves weather forecast by city name and country code
     * @param city City Name
     * @param country Country Code
     * @return the requested {@link HourlyForecast}
     */
    HourlyForecast hourlyForecastByCityAndCountryCode(String city, String country);

    /**
     * Retrieves weather forecast by city id
     * @param id City Id
     * @return the requested {@link HourlyForecast}
     */
    HourlyForecast hourlyForecastByCityId(String id);

    /**
     * Retrieves weather forecast by geographic coordinates
     * @param lat Latitude
     * @param lon Longitude
     * @return the requested {@link HourlyForecast}
     */
    HourlyForecast dailyForecastByLatLon(double lat, double lon);

}
