package ianic.simpleweatherforecast.util;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import com.google.android.youtube.player.YouTubePlayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ianic.simpleweatherforecast.R;
import ianic.simpleweatherforecast.activities.MainActivity;
import ianic.simpleweatherforecast.activities.VideoPlayerActivity;

/**
 * A controller which offers methods to control the video in {@linkplain VideoPlayerActivity}
 * via buttons.
 *
 * @author Ivan Anić
 * @version 1.0
 */
public class PlayerController {

    private final YouTubePlayer player;
    private Context activity;

    public PlayerController(YouTubePlayer player, Context _activity) {
        this.player = player;
        this.activity = _activity;
        ButterKnife.bind(((VideoPlayerActivity) activity));
    }

    @OnClick(R.id.buttonPlay)
    void play() {
        player.play();
    }


    @OnClick(R.id.buttonPause)
    void pause() {
        player.pause();
    }

    @OnClick(R.id.buttonBack)
    void returnToPrevious() {
        ((VideoPlayerActivity) activity).finish();
    }

    @BindView(R.id.timeEdit)
    EditText timeGet;

    @OnClick(R.id.buttonForward)
    void timeSet() {
        if (timeGet.getText().toString().length() > 0) {
            player.seekToMillis(Integer.parseInt(timeGet.getText().toString()) * 1000);
        }
    }

    @OnClick(R.id.buttonHome)
    void home() {
        Intent home_intent = new Intent(activity, MainActivity.class);
        home_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(home_intent);
        ((VideoPlayerActivity) activity).finish();
    }
}