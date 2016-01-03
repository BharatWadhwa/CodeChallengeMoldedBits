package com.challenge.moldedbits.utils;

import android.content.Context;
import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created by bharat on 03/01/16.
 */
public class HttpClient {
    public static final String TAG = HttpClient.class.getSimpleName();
    private static final long HTTP_CACHE_SIZE = 1024 * 1024 * 20; // 20MB
    private static final int DEFAULT_RETRY_COUNT = 1;
    private OkHttpClient okClient;
    private static HttpClient httpClient;

    public static final void init(Context context) {
        try {
            httpClient = new HttpClient(context);
        } catch (Exception e) {

        }
    }

    private HttpClient(Context context) {
        this.okClient = new OkHttpClient();
        this.okClient.setConnectTimeout(30, TimeUnit.SECONDS);
        this.okClient.setReadTimeout(30, TimeUnit.SECONDS);
        this.okClient.setWriteTimeout(30, TimeUnit.SECONDS);

        Cache cache = null;
        File cacheDir = context.getCacheDir();
        cache = new Cache(cacheDir, HTTP_CACHE_SIZE);
        okClient.setCache(cache);
    }

    public static HttpClient getInstance() {
        if (httpClient == null) {
            Log.e(TAG, "HttpClient has not been initialized!");
        }
        return httpClient;
    }

    public <T> T executeGet(Class<T> returnType, String url, int... retryCount) throws IOException {
        Request request = getRequestBuilder(url).build();
        return execute(returnType, request, retryCount);
    }

    public <T> T execute(Class<T> returnType, Request request, int... retryCount) throws IOException {
        Response response = newCall(request, retryCount);

        if (InputStream.class.equals(returnType)) {
            return (T) response.body().byteStream();
        } else if (Response.class.equals(returnType)) {
            return (T) response;
        } else if (String.class.equals(returnType)) {
            return (T) response.body().string();
        } else if (JSONObject.class.equals(returnType)) {
            String responseString = response.body().string();
            try {
                JSONObject jsonObject = new JSONObject(responseString);
                return (T) jsonObject;
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage(), e);
                return null;
            }
        } else if (JSONArray.class.equals(returnType)) {
            String responseString = response.body().string();
            try {
                JSONArray jsonArray = new JSONArray(responseString);
                return (T) jsonArray;
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage(), e);
                return null;
            }
        }
        throw new RuntimeException("Unsupported Return Type: " + returnType.getCanonicalName());
    }

    public Request.Builder getRequestBuilder(String url) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        return builder;
    }

    public Response newCall(Request request, int... retryCount) throws IOException {
        int lRetryCount = DEFAULT_RETRY_COUNT;
        if (retryCount.length > 0) {
            lRetryCount = retryCount[0];
        }

        int retries = 0;
        Response finalResponse = null;
        while (retries <= lRetryCount && (finalResponse == null || !finalResponse.isSuccessful())) {
            try {
                if (retries > 0) {
                    Log.i(TAG, "Retrying...");
                }
                Log.i(TAG, request.method() + " " + request.urlString());
                Response response = okClient.newCall(request).execute();
                if (response != null) {
                    finalResponse = response;
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
            retries++;
        }

        if (finalResponse != null && finalResponse.isSuccessful()) {
            Log.i(TAG, request.method() + " " + request.urlString() + " completed with code: " + finalResponse.code() + ". Served from cache: "
                    + (finalResponse.cacheResponse() != null));
            return finalResponse;
        }

        String errorString = request.method() + " " + request.urlString();
        if (finalResponse != null) {
            errorString += " failed with code: " + finalResponse.code() + ". Attempts: " + retries;
        } else {
            errorString += " failed. Attempts: " + retries;
        }

        Log.e(TAG, errorString);
        throw new IOException(errorString);
    }
}
