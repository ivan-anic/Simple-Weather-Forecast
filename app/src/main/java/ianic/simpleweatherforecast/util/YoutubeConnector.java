package ianic.simpleweatherforecast.util;

import android.content.Context;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.List;

import ianic.simpleweatherforecast.R;
import ianic.simpleweatherforecast.activities.VideoPlayerActivity;

/**
 * A connector which performs Youtube queries for the {@linkplain VideoPlayerActivity}
 *
 * @author Ivan Anić
 * @version 1.0
 */
public class YoutubeConnector {
    private YouTube.Search.List query;

    public static final String KEY
            = "AIzaSyC5jIoawDsjcqzXJaGB-QAeCXczkVFsLuk";

    private Context mContext;

    public YoutubeConnector(Context context) {
        this.mContext = context;

        YouTube youtube = new YouTube.Builder(new NetHttpTransport(),
                new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest hr) throws IOException {
            }
        }).setApplicationName(context.getString(R.string.app_name)).build();

        try {
            query = youtube.search().list(context.getString(R.string.youtube_search_list));
            query.setKey(KEY);
            query.setType(context.getString(R.string.youtube_type));
            query.setFields(context.getString(R.string.youtube_fields));
            query.setMaxResults(1L);
        } catch (Exception e) {
            SystemUtil.createDialogExportFinished(mContext,
                    R.string.youtube_alert_alert,
                    R.string.youtube_error_message,
                    true);
        }
    }

    public String search(String keywords) {
        query.setQ(keywords);
        try {
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();

            return results.get(0).getId().getVideoId();
        } catch (Exception e) {
            SystemUtil.createDialogExportFinished(mContext,
                    R.string.youtube_alert_alert,
                    R.string.youtube_error_message,
                    true);
            return null;
        }
    }
}