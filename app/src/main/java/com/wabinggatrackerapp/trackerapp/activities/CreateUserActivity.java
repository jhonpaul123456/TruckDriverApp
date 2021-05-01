package com.wabinggatrackerapp.trackerapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.wabinggatrackerapp.trackerapp.R;
import com.wabinggatrackerapp.trackerapp.databinding.ActivityCreateUserBinding;
import com.wabinggatrackerapp.trackerapp.model.Shizuka;
import com.wabinggatrackerapp.trackerapp.network.CreateUserService;
import com.wabinggatrackerapp.trackerapp.parents.BigPapaActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserActivity extends BigPapaActivity {

    ActivityCreateUserBinding binding;
    CreateUserService mCreateUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_user);

        mCreateUserService = getRetrofit().create(CreateUserService.class);

        binding.createUserBtn.setOnClickListener(v -> {
            if (clearValidations()) {
                createUser(
                        binding.name.getText().toString().trim(),
                        binding.mobile.getText().toString().trim(),
                        binding.street.getText().toString().trim(),
                        binding.total.getText().toString().trim(),
                        binding.dates.getText().toString().trim(),
                        binding.status.getText().toString().trim()
                        );
            }
        });
    }

    // This works asynchronously
    private void createUser(String name, String mobile, String street, String total, String dates, String status) {
        Toast.makeText(this, R.string.please_wait, Toast.LENGTH_SHORT).show();
        disableViews(binding.name, binding.mobile, binding.street, binding.total, binding.dates, binding.status, binding.createUserBtn);
        mCreateUserService.createUser(name, mobile, street, total, dates, status).enqueue(new Callback<Shizuka>() {
            @Override
            public void onResponse(Call<Shizuka> call, Response<Shizuka> response) {
                enableViews(binding.name, binding.mobile, binding.street, binding.total, binding.dates, binding.status, binding.createUserBtn);
                Toast.makeText(CreateUserActivity.this, "User Created !", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(CreateUserActivity.this, FetchUsersActivity.class));
            }

            @Override
            public void onFailure(Call<Shizuka> call, Throwable t) {
                enableViews(binding.name, binding.mobile, binding.street, binding.total, binding.dates, binding.status, binding.createUserBtn);
                t.printStackTrace();
                Toast.makeText(CreateUserActivity.this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean clearValidations() {
        boolean pass = false;
        if (binding.name.getText().toString().trim().isEmpty()) {
            binding.name.setError(getString(R.string.please_enter_customers_name));
            binding.name.requestFocus();
        } else if (binding.mobile.getText().toString().trim().isEmpty()) {
            binding.mobile.setError(getString(R.string.please_enter_customers_number));
            binding.mobile.requestFocus();
        } else if (binding.street.getText().toString().trim().isEmpty()) {
            binding.street.setError(getString(R.string.please_enter_customers_street));
            binding.street.requestFocus();
        } else if (binding.total.getText().toString().trim().isEmpty()) {
            binding.total.setError(getString(R.string.please_enter_total));
            binding.total.requestFocus();
        } else if (binding.dates.getText().toString().trim().isEmpty()){
            binding.dates.setError(getString(R.string.date_to_deliver));
            binding.dates.requestFocus();
        }else if(binding.status.getText().toString().trim().isEmpty()) {
            binding.status.setError(getString(R.string.paid_or_not));
            binding.status.requestFocus();
        }else{
            binding.name.setError(null);
            binding.mobile.setError(null);
            binding.street.setError(null);
            binding.total.setError(null);
            binding.dates.setError(null);
            binding.status.setError(null);
            pass = true;
        }
        return pass;
    }
}