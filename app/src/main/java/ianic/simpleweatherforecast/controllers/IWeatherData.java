package ianic.simpleweatherforecast.controllers;

import ianic.simpleweatherforecast.models.HttpResponses.Weather.WeatherData;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * An interface used by {@code Retrofit} library which connects to the specified api
 * and retrieves data from the server.
 *
 * @author Ivan Anić
 * @version 1.0
 */
public interface IWeatherData {

    @GET("data/2.5/weather?")
    Observable<WeatherData> getWeatherFromApi(
            @Query("q") String cityName,
            @Query("units") String units,
            @Query("APPID") String appId);
}
