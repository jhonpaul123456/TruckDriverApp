package com.firstapp.crudappprojectlcnostrd.parents;

import androidx.appcompat.app.AppCompatActivity;

import com.firstapp.crudappprojectlcnostrd.network.RetroClient;

public class BigPapaActivity extends AppCompatActivity {

    RetroClient mRetroClient;

    public RetroClient getRetroClient() {
        if (mRetroClient == null) {
            mRetroClient = new RetroClient();
        }
        return mRetroClient;
    }
}
