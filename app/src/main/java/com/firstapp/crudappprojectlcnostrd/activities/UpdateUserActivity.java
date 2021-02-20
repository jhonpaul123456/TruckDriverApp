package com.firstapp.crudappprojectlcnostrd.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.databinding.ActivityUpdateUserBinding;
import com.firstapp.crudappprojectlcnostrd.model.User;
import com.firstapp.crudappprojectlcnostrd.network.FetchUserService;
import com.firstapp.crudappprojectlcnostrd.parents.BigPapaActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserActivity extends BigPapaActivity {

    ActivityUpdateUserBinding binding;
    FetchUserService mFetchUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_user);

        binding.updateUserBtn.setEnabled(false);
        mFetchUserService = getRetrofit().create(FetchUserService.class);

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