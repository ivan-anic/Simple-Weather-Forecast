package ianic.simpleweatherforecast.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.todddavies.components.progressbar.ProgressWheel;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ianic.simpleweatherforecast.R;
import ianic.simpleweatherforecast.controllers.RfAdapter;
import ianic.simpleweatherforecast.models.HttpResponses.Forecast.ForecastData;
import ianic.simpleweatherforecast.models.HttpResponses.Weather.WeatherData;
import ianic.simpleweatherforecast.models.WeatherCombined;
import ianic.simpleweatherforecast.util.WeatherInfoDisplayUtil;
import ianic.simpleweatherforecast.util.YoutubeConnector;

/**
 * The activity which displays the current weather, and the forecasted weather
 * for the desired city. Also offers a link to a video which can describe the
 * current weather. {@linkplain VideoPlayerActivity}
 *
 * @author Ivan Anić
 * @version 1.0
 */
public class WeatherDisplayActivity extends AppCompatActivity {

    private WeatherData currentWeather;
    private String currentlyQueriedCity;

    @BindView(R.id.text_header)
    TextView tvHeader;

    @BindView(R.id.tvCurrentTemp)
    TextView tvCurrentTemp;

    @BindView(R.id.tvCurrentWeather)
    TextView tvCurrentWeather;

    @BindView(R.id.buttonVideo)
    ImageButton buttonVideo;

    @BindView(R.id.tvBox1)
    TextView tvBox1;
    @BindView(R.id.tvBox2)
    TextView tvBox2;
    @BindView(R.id.tvBox3)
    TextView tvBox3;

    @BindView(R.id.imageCurrentWeather)
    ImageView icon;

    @BindView(R.id.pw_spinner)
    ProgressWheel progressWheel;

    @OnClick(R.id.buttonVideo)
    void play() {
        new Thread() {
            public void run() {
                YoutubeConnector yc = new YoutubeConnector(WeatherDisplayActivity.this);

                final String searchResult = yc.search(
                        currentWeather.getWeather().get(0).getDescription() + " " + currentlyQueriedCity);

                handler.post(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(WeatherDisplayActivity.this, VideoPlayerActivity.class);
                        intent.putExtra("VIDEO_ID", searchResult);
                        startActivity(intent);
                    }
                });
            }
        }.start();
    }

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherdisplay);
        ButterKnife.bind(this);

        currentlyQueriedCity = getIntent().getExtras().getString("city");

        handler = new android.os.Handler();

        progressWheel.startSpinning();
    }

    @Override
    protected void onStart() {
        super.onStart();
        expandWeatherAsync();
    }

    public void expandWeatherAsync() {
        RfAdapter rfObservablesAdapter = new RfAdapter(currentlyQueriedCity);
        rfObservablesAdapter.setAppContext(this);
    }

    public void updateUX(WeatherCombined combinedData) {
        updateUXWithPng(combinedData);
        updateUXWithWeatherData(combinedData);
    }

    private void updateUXWithPng(final WeatherCombined combinedData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                currentWeather = combinedData.data;
                String url = String.format(
                        getResources().getString(R.string.url_png),
                        combinedData.data.getWeather().get(0).getIcon());

                Picasso.with(getApplicationContext())
                        .load(url)
                        .into(icon);
            }
        });
        updateUXWithWeatherData(combinedData);
    }

    private void updateUXWithWeatherData(final WeatherCombined combinedData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ForecastData forecastData = combinedData.forecast;
                List<ForecastData.List> list = forecastData.getList();

                progressWheel.stopSpinning();
                progressWheel.setVisibility(View.GONE);

                currentlyQueriedCity = currentWeather.getName();

                tvCurrentTemp.setText(
                        WeatherInfoDisplayUtil.generateCurrentTemperatureString(currentWeather));

                tvHeader.setText(
                        WeatherInfoDisplayUtil.generateHeaderString(
                                currentlyQueriedCity, getApplicationContext()));

                tvCurrentWeather.setText(
                        WeatherInfoDisplayUtil.generateCurrentWeatherString(combinedData));

                Calendar cal = Calendar.getInstance();

                cal.add(Calendar.DATE, 1);
                tvBox1.setText(
                        WeatherInfoDisplayUtil.generateForecastString(cal, list.get(1)));
                cal.add(Calendar.DATE, 1);
                tvBox2.setText(
                        WeatherInfoDisplayUtil.generateForecastString(cal, list.get(2)));
                cal.add(Calendar.DATE, 1);
                tvBox3.setText(
                        WeatherInfoDisplayUtil.generateForecastString(cal, list.get(3)));
            }
        });
    }
}