package com.firstapp.crudappprojectlcnostrd.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Glide.with(this).load("https://cdn.dribbble.com/users/476608/screenshots/9633341/media/0762048de839209981d134832fc5ce9a.png?compress=1&resize=400x300").into(binding.truckLogo);

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

    }
}