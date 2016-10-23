package ianic.simpleweatherforecast.util;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ianic.simpleweatherforecast.R;
import ianic.simpleweatherforecast.models.HttpResponses.Forecast.ForecastData;
import ianic.simpleweatherforecast.models.HttpResponses.Forecast.ForecastData.Temp;
import ianic.simpleweatherforecast.models.HttpResponses.Weather.WeatherData;
import ianic.simpleweatherforecast.models.HttpResponses.Weather.WeatherData.Main;
import ianic.simpleweatherforecast.models.HttpResponses.Weather.WeatherData.Wind;
import ianic.simpleweatherforecast.models.WeatherCombined;

/**
 * A utility class which offers methods which generate the strings which will be inflated into
 * the corresponding views and presented to the end user.
 *
 * @author Ivan Anić
 * @version 1.0
 */
public class WeatherInfoDisplayUtil {

    public static String generateCurrentTemperatureString(WeatherData currentWeather) {
        return String.valueOf(Math.round(currentWeather.getMain().getTemp())) + "°C";
    }

    public static String generateHeaderString(String city, Context context) {
        return String.format(
                context.getString(R.string.forecast_header) + " ", city);
    }

    public static String generateCurrentWeatherString(WeatherCombined data) {
        Temp temp = data.forecast.getList().get(0).getTemp();
        Wind wind = data.data.getWind();
        Main m = data.data.getMain();

        return data.data.getWeather().get(0).getDescription() + "\n" +
                Math.round(temp.getMax()) + "°/" +
                Math.round(temp.getMin()) + "°\n\n" +
                "humidity: " + String.valueOf(m.getHumidity()) + "%" + "\n" +
                "wind: " + Math.round(wind.getDeg()) + "° " + wind.getSpeed() + " m/s" + "\n" +
                "pressure: " + Math.round(m.getPressure()) + " hPa";
    }

    public static String generateForecastString(Calendar cal, ForecastData.List list) {
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(cal.getTime().getTime()) +
                "\n\nday: " + Math.round(list.getTemp().getDay()) + " °C"
                + "\nnight: " + Math.round(list.getTemp().getNight()) + " °C";
    }

}
