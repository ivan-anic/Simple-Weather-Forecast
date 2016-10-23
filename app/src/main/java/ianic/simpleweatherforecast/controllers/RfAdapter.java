package ianic.simpleweatherforecast.controllers;

import android.util.Log;

import java.lang.ref.WeakReference;

import ianic.simpleweatherforecast.R;
import ianic.simpleweatherforecast.activities.WeatherDisplayActivity;
import ianic.simpleweatherforecast.models.HttpResponses.Forecast.ForecastData;
import ianic.simpleweatherforecast.models.HttpResponses.Weather.WeatherData;
import ianic.simpleweatherforecast.models.WeatherCombined;
import ianic.simpleweatherforecast.util.SystemUtil;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import static rx.Observable.zip;

/**
 * An adapter used by {@code Retrofit} library which connects to the specified api
 * and retrieves data from the server.
 *
 * @author Ivan Anić
 * @version 1.0
 */
public class RfAdapter {

    private WeakReference<WeatherDisplayActivity> mActivityRef;
    private static final String WEATHER_URL = "http://api.openweathermap.org/";
    private static final String OPEN_WEATHER_API = "8119e5a060c3b6d13d52da113ee87591";

    public RfAdapter(String city) {

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

        Observable<ForecastData> forecastObservable = retrofit.create(IForecastData.class)
                .getForecast(city, "metric", "4", OPEN_WEATHER_API);

        Observable<WeatherData> weatherObservable = retrofit.create(IWeatherData.class)
                .getWeatherFromApi(city, "metric", OPEN_WEATHER_API);

        //an observable which merges the two api calls and returns the combined POJO
        Observable<WeatherCombined> combined = zip(forecastObservable, weatherObservable,
                new Func2<ForecastData, WeatherData, WeatherCombined>() {
                    @Override
                    public WeatherCombined call(ForecastData jsonObject, WeatherData jsonElements) {
                        return new WeatherCombined(jsonObject, jsonElements);
                    }
                });

        combined
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherCombined>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        SystemUtil.createDialogExportFinished(mActivityRef.get(),
                                R.string.api_not_availible_alert,
                                R.string.api_not_availible_message, true);
                    }

                    @Override
                    public void onNext(WeatherCombined weatherData) {
                        mActivityRef.get().updateUX(weatherData);
                    }
                });
    }

    public void setAppContext(WeatherDisplayActivity ref) {
        mActivityRef = new WeakReference<>(ref);
    }
}
