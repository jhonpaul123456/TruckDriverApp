package com.firstapp.crudappprojectlcnostrd.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.activities.auth.LoginActivity;
import com.firstapp.crudappprojectlcnostrd.databinding.ActivitySplashBinding;
import com.firstapp.crudappprojectlcnostrd.utils.PrefsHelper;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding mBinding;
    PrefsHelper mPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        mPrefsHelper = new PrefsHelper(getSharedPreferences(PrefsHelper.USER_PREFERENCE_FILE, Context.MODE_PRIVATE));

        Glide.with(this).load("https://i.imgur.com/TRFTZNZ.png").into(mBinding.truckLogo);

        mBinding.startBtn.setOnClickListener(v -> {
            if (mPrefsHelper.isUserLoggedIn()) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
            finish(); // Remove this line if you don't want this activity to be closed
            // once the user passed to main activity.
        });

    }
}