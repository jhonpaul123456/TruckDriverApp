package com.firstapp.crudappprojectlcnostrd.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.createBtn.setOnClickListener(v -> {

        });

        binding.deleteUser.setOnClickListener(v -> {

        });

        binding.fetchUser.setOnClickListener(v -> {

        });

        binding.updateUser.setOnClickListener(v -> {

        });

    }
}