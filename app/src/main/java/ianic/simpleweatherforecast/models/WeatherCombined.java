package ianic.simpleweatherforecast.models;

import ianic.simpleweatherforecast.models.HttpResponses.Forecast.ForecastData;
import ianic.simpleweatherforecast.models.HttpResponses.Weather.WeatherData;

/**
 * A model which holds the merged data from two api requests;
 * <ul>
 * <li>{@linkplain ForecastData}</li>
 * <li>{@linkplain WeatherData}</li>
 * </ul>
 *
 * @author Ivan Anić
 * @version 1.0
 */
public class WeatherCombined {

    public ForecastData forecast;
    public WeatherData data;

    public WeatherCombined(ForecastData forecast, WeatherData data) {
        this.forecast = forecast;
        this.data = data;
    }
}


