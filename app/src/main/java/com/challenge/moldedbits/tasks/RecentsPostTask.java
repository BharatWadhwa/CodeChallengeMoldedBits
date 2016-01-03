package com.challenge.moldedbits.tasks;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import com.challenge.moldedbits.models.RecentPosts;
import com.challenge.moldedbits.utils.HttpClient;

/**
 * Created by bharat on 03/01/16.
 */
public class RecentsPostTask extends AsyncTask<String, Integer, RecentPosts> {

    public static final String RECENT_POST_URL = "https://alpha-api.app.net/stream/0/posts/stream/global";

    @Override
    protected RecentPosts doInBackground(String... params) {
        try {
            String response = HttpClient.getInstance().executeGet(String.class, RECENT_POST_URL, 2);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            RecentPosts recentPosts = objectMapper.readValue(response, RecentPosts.class);
            return recentPosts;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
