package ianic.simpleweatherforecast.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ianic.simpleweatherforecast.R;
import ianic.simpleweatherforecast.util.PlayerController;
import ianic.simpleweatherforecast.util.YoutubeConnector;

/**
 * The activity which described the current weather situation as a youtube video. The activity
 * also offers on-screen controls to manipulate the video playback and activity flow.
 *
 * @author Ivan Anić
 * @version 1.0
 */
public class VideoPlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayer player;

    @OnClick(R.id.buttonPlay)
    void play() {
        player.play();
    }

    @OnClick(R.id.buttonPause)
    void pause() {
        player.pause();
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
        Intent home_intent = new Intent(VideoPlayerActivity.this, MainActivity.class);
        home_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
        finish();
    }

    @BindView(R.id.player_view)
    YouTubePlayerView playerView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_video_player);

        ButterKnife.bind(this);

        playerView.initialize(YoutubeConnector.KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        final YouTubePlayer player,
                                        boolean restored) {
        if (!restored) {
            this.player = player;

            PlayerController controller = new PlayerController(player, this);
            ButterKnife.bind(controller, this);

            player.cueVideo(getIntent().getStringExtra("VIDEO_ID"));

        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider
                                                provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_LONG).show();

    }
}
