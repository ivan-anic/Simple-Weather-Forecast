package ianic.simpleweatherforecast.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ianic.simpleweatherforecast.R;
import ianic.simpleweatherforecast.models.NoFocusEditText;
import ianic.simpleweatherforecast.util.ComponentUtil;
import ianic.simpleweatherforecast.util.SystemUtil;

/**
 * The starting activity of the application, offers the option to query the server for a desired
 * city for which the application will retrieve the weather data.
 *
 * @author Ivan Anić
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etSearch)
    NoFocusEditText etSearch;

    @OnClick(R.id.buttonSearch)
    void launchForecast() {
        if (!ComponentUtil.checkTextField(etSearch))
            return;

        if (!SystemUtil.isInternetAvailable()) {
            SystemUtil.createDialogExportFinished(
                    this,
                    R.string.internet_unavailible_alert,
                    R.string.internet_unavailible_message,
                    false);
            clearSearchField();
            etSearch.clearFocus();
            return;
        }

        Intent intent = new Intent(MainActivity.this, WeatherDisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("city", String.valueOf(etSearch.getText()));

        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        clearSearchField();
    }

    private void clearSearchField() {
        etSearch.setText("");
    }
}
