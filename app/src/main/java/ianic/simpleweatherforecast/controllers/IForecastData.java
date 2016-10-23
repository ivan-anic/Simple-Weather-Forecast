package ianic.simpleweatherforecast.controllers;

import ianic.simpleweatherforecast.models.HttpResponses.Forecast.ForecastData;
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
public interface IForecastData {

    @GET("data/2.5/forecast/daily?")
    Observable<ForecastData> getForecast(
            @Query("q") String cityName,
            @Query("units") String units,
            @Query("cnt") String cnt,
            @Query("APPID") String appId);
}
