package com.firstapp.crudappprojectlcnostrd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.databinding.ActivityUpdateUserBinding;
import com.firstapp.crudappprojectlcnostrd.model.User;
import com.firstapp.crudappprojectlcnostrd.network.FetchUserService;
import com.firstapp.crudappprojectlcnostrd.network.UpdateUserService;
import com.firstapp.crudappprojectlcnostrd.parents.BigPapaActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserActivity extends BigPapaActivity {

    ActivityUpdateUserBinding binding;
    FetchUserService mFetchUserService;
    UpdateUserService mUpdateUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_user);

        binding.updateUserBtn.setEnabled(false);
        mFetchUserService = getRetrofit().create(FetchUserService.class);
        mUpdateUserService = getRetrofit().create(UpdateUserService.class);

        binding.id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.updateUserBtn.setEnabled(false);
                fetchAndSetUser(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.updateUserBtn.setOnClickListener(v -> {
            if (clearValidations()) {
                disableViews(binding.updateUserBtn);
                mUpdateUserService.updateUser(
                        binding.id.getText().toString().trim(),
                        binding.driverName.getText().toString().trim(),
                        binding.plateNumber.getText().toString().trim(),
                        binding.truckCondition.getText().toString().trim(),
                        binding.truckType.getText().toString().trim()
                ).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        enableViews(binding.updateUserBtn);
                        if (response.body().isStatusSuccess()) {
                            Toast.makeText(UpdateUserActivity.this, "Data Updated !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateUserActivity.this, FetchUsersActivity.class));
                            finish();
                        } else {
                            Toast.makeText(UpdateUserActivity.this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        enableViews(binding.updateUserBtn);
                        Toast.makeText(UpdateUserActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private boolean clearValidations() {
        boolean pass = false;
        if (binding.id.getText().toString().trim().isEmpty()) {
            binding.id.setError(getString(R.string.please_enter_id));
            binding.id.requestFocus();
        } else if (binding.driverName.getText().toString().trim().isEmpty()) {
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
            binding.id.setError(null);
            binding.driverName.setError(null);
            binding.plateNumber.setError(null);
            binding.truckCondition.setError(null);
            binding.truckType.setError(null);
            pass = true;
        }
        return pass;
    }

    private void fetchAndSetUser(String id) {
        mFetchUserService.getUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().isStatusSuccess()) {
                    binding.updateUserBtn.setEnabled(true);
                    binding.driverName.setText(response.body().getDriverName());
                    binding.plateNumber.setText(response.body().getPlateNumber());
                    binding.truckCondition.setText(response.body().getTruckCondition());
                    binding.truckType.setText(response.body().getTruckType());
                } else {
                    // todo: this thing need to be fixed.
                    Toast.makeText(UpdateUserActivity.this, "No User Found with id: " + id, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UpdateUserActivity.this, "oOps!, Something went wrong!", Toast.LENGTH_SHORT).show();
                binding.updateUserBtn.setEnabled(false);
            }
        });
    }
}