package com.firstapp.crudappprojectlcnostrd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.databinding.ActivityCreateUserBinding;
import com.firstapp.crudappprojectlcnostrd.model.User;
import com.firstapp.crudappprojectlcnostrd.network.CreateUserService;
import com.firstapp.crudappprojectlcnostrd.parents.BigPapaActivity;

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
                        binding.driverName.getText().toString().trim(),
                        binding.plateNumber.getText().toString().trim(),
                        binding.truckCondition.getText().toString().trim(),
                        binding.truckType.getText().toString().trim()
                        );
            }
        });
    }

    // This works asynchronously
    private void createUser(String driverName, String plateNumber, String truckCondition, String truckType) {
        Toast.makeText(this, R.string.please_wait, Toast.LENGTH_SHORT).show();
        disableViews(binding.driverName, binding.truckCondition, binding.plateNumber, binding.truckType, binding.createUserBtn);
        mCreateUserService.createUser(driverName, plateNumber, truckCondition, truckType).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                enableViews(binding.driverName, binding.truckCondition, binding.plateNumber, binding.truckType, binding.createUserBtn);
                Toast.makeText(CreateUserActivity.this, "User Created !", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(CreateUserActivity.this, FetchUsersActivity.class));
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                enableViews(binding.driverName, binding.truckCondition, binding.plateNumber, binding.truckType, binding.createUserBtn);
                t.printStackTrace();
                Toast.makeText(CreateUserActivity.this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean clearValidations() {
        boolean pass = false;
        if (binding.driverName.getText().toString().trim().isEmpty()) {
            binding.driverName.setError(getString(R.string.please_enter_driver_name));
            binding.driverName.requestFocus();
        } else if (binding.plateNumber.getText().toString().trim().isEmpty()) {
            binding.plateNumber.setError(getString(R.string.please_enter_plate_number));
            binding.plateNumber.requestFocus();
        } else if (binding.truckCondition.getText().toString().trim().isEmpty()) {
            binding.truckCondition.setError(getString(R.string.please_enter_truck_condition));
            binding.truckCondition.requestFocus();
        } else if (binding.truckType.getText().toString().trim().isEmpty()) {
            binding.truckType.setError(getString(R.string.please_enter_truck_type));
            binding.truckType.requestFocus();
        } else {
            binding.driverName.setError(null);
            binding.plateNumber.setError(null);
            binding.truckCondition.setError(null);
            binding.truckType.setError(null);
            pass = true;
        }
        return pass;
    }
}