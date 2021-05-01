package com.wabinggatrackerapp.trackerapp.activities.auth;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.wabinggatrackerapp.trackerapp.R;
import com.wabinggatrackerapp.trackerapp.databinding.ActivityRegistrationBinding;
import com.wabinggatrackerapp.trackerapp.model.databse.Driver;
import com.wabinggatrackerapp.trackerapp.model.repository.DriverRepo;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivity";
    ActivityRegistrationBinding mBinding;
    DriverRepo mDriverRepo;
    CompositeDisposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration);

        mDriverRepo = new DriverRepo(getApplication());
        mDisposable = new CompositeDisposable();

        mBinding.registerBtn.setOnClickListener(v -> {
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
                register(username, password);
            }
        });
    }

    private void register(String username, String password) {
        mDriverRepo.getDriver(username).observe(this, drivers -> {
            if (drivers.size() == 0) {
                mDriverRepo.insert(new Driver(username, password));
                Toast.makeText(this, "User Registered Successfully !", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}