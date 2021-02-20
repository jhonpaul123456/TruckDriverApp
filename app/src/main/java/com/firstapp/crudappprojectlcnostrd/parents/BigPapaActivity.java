package com.firstapp.crudappprojectlcnostrd.parents;

import androidx.appcompat.app.AppCompatActivity;

import com.firstapp.crudappprojectlcnostrd.network.RetroClient;

import retrofit2.Retrofit;

public class BigPapaActivity extends AppCompatActivity {

    RetroClient mRetroClient;

    public Retrofit getRetrofit() {
        if (mRetroClient == null) {
            mRetroClient = new RetroClient();
        }
        return mRetroClient.getRetrofit();
    }
}
