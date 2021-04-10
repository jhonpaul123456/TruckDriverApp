package com.firstapp.crudappprojectlcnostrd.activities.auth;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.databinding.ActivityRegistrationBinding;
import com.firstapp.crudappprojectlcnostrd.model.databse.Driver;
import com.firstapp.crudappprojectlcnostrd.model.repository.DriverRepo;

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
            String password = mBinding.pasword.getText().toString().trim();

            if (username.isEmpty()) {
                mBinding.username.setError("Please Enter Username");
                mBinding.username.requestFocus();
                return;
            } else if (password.isEmpty()) {
                mBinding.pasword.setError("Please Enter Password");
                mBinding.pasword.requestFocus();
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