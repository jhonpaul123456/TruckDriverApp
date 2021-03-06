package com.wabinggatrackerapp.trackerapp.parents;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wabinggatrackerapp.trackerapp.network.RetroClient;

import retrofit2.Retrofit;

public class BigPapaActivity extends AppCompatActivity {

    RetroClient mRetroClient;

    public Retrofit getRetrofit() {
        if (mRetroClient == null) {
            mRetroClient = new RetroClient();
        }
        return mRetroClient.getRetrofit();
    }

    public void enableViews(View... views) {
        for (View v : views) {
            v.setEnabled(true);
        }
    }

    public void disableViews(View... views) {
        for (View v : views) {
            v.setEnabled(false);
        }
    }
}
