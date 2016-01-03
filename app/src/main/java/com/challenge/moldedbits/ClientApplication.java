package com.challenge.moldedbits;

import android.app.Application;

import com.challenge.moldedbits.utils.HttpClient;

/**
 * Created by bharat on 03/01/16.
 */
public class ClientApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        HttpClient.init(this);
    }
}
