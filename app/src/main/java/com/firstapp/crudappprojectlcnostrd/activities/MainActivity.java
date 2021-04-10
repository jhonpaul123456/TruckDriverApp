package com.firstapp.crudappprojectlcnostrd.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.databinding.ActivityMainBinding;
import com.firstapp.crudappprojectlcnostrd.utils.PrefsHelper;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    PrefsHelper mPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mPrefsHelper = new PrefsHelper(getSharedPreferences(PrefsHelper.USER_PREFERENCE_FILE, Context.MODE_PRIVATE));

        Glide.with(this).load("https://i.imgur.com/TRFTZNZ.png").into(binding.truckLogo);

        binding.createBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, CreateUserActivity.class));
        });

        binding.deleteUser.setOnClickListener(v -> {
            startActivity(new Intent(this, DeleteUserActivity.class));
        });

        binding.fetchUser.setOnClickListener(v -> {
            startActivity(new Intent(this, FetchUsersActivity.class));
        });

        binding.updateUser.setOnClickListener(v -> {
            startActivity(new Intent(this, UpdateUserActivity.class));
        });

        binding.logoutBtn.setOnClickListener(v -> {
            mPrefsHelper.logout();
            startActivity(new Intent(this, SplashActivity.class));
            finish();
        });
    }
}