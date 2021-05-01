package com.wabinggatrackerapp.trackerapp.activities.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.wabinggatrackerapp.trackerapp.R;
import com.wabinggatrackerapp.trackerapp.activities.MainActivity;
import com.wabinggatrackerapp.trackerapp.databinding.ActivityLoginBinding;
import com.wabinggatrackerapp.trackerapp.model.databse.Driver;
import com.wabinggatrackerapp.trackerapp.model.repository.DriverRepo;
import com.wabinggatrackerapp.trackerapp.utils.PrefsHelper;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding mBinding;
    DriverRepo mDriverRepo;
    PrefsHelper mPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        mDriverRepo = new DriverRepo(getApplication());
        mPrefsHelper = new PrefsHelper(getSharedPreferences(PrefsHelper.USER_PREFERENCE_FILE, Context.MODE_PRIVATE));

        mBinding.loginBtn.setOnClickListener(v -> {
            String username = mBinding.username.getText().toString().trim();
            String password = mBinding.password.getText().toString().trim();

            if (username.isEmpty()) {
                mBinding.username.setError("Please Enter Username");
                mBinding.username.requestFocus();
                return;
            } else if (password.isEmpty()) {
                mBinding.password.setError("Please Enter Password");
                mBinding.password.requestFocus();
                return;
            } else {
                login(username, password);
            }
        });

        mBinding.createAccount.setOnClickListener(v -> {
            startActivity(new Intent(this, RegistrationActivity.class));
        });
    }

    private void login(String username, String password) {
        mDriverRepo.getDriver(username).observe(this, new Observer<List<Driver>>() {
            @Override
            public void onChanged(List<Driver> drivers) {
                if (drivers.size() == 0) {
                    Toast.makeText(LoginActivity.this, "No user found!", Toast.LENGTH_SHORT).show();
                } else if (!drivers.get(0).getPassword().equals(password)) {
                    Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                } else {
                    mPrefsHelper.setUserAsLoggedIn(String.valueOf(drivers.get(0).getId()),
                            drivers.get(0).getUsername(), drivers.get(0).getPassword());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}