package com.wabinggatrackerapp.trackerapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.wabinggatrackerapp.trackerapp.R;
import com.wabinggatrackerapp.trackerapp.databinding.ActivityUpdateUserBinding;
import com.wabinggatrackerapp.trackerapp.model.Shizuka;
import com.wabinggatrackerapp.trackerapp.network.FetchUserService;
import com.wabinggatrackerapp.trackerapp.network.UpdateUserService;
import com.wabinggatrackerapp.trackerapp.parents.BigPapaActivity;

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
                        binding.name.getText().toString().trim(),
                        binding.mobile.getText().toString().trim(),
                        binding.street.getText().toString().trim(),
                        binding.total.getText().toString().trim(),
                        binding.dates.getText().toString().trim(),
                        binding.status.getText().toString().trim()
                ).enqueue(new Callback<Shizuka>() {
                    @Override
                    public void onResponse(Call<Shizuka> call, Response<Shizuka> response) {
                        enableViews(binding.updateUserBtn);
                        if (response.isSuccessful()) {
                            Toast.makeText(UpdateUserActivity.this, "Data Updated !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateUserActivity.this, FetchUsersActivity.class));
                            finish();
                        } else {
                            Log.e("WhoAmI", "onResponse: " + response.body().toString());
                            Toast.makeText(UpdateUserActivity.this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Shizuka> call, Throwable t) {
                        t.printStackTrace();
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
        } else if (binding.name.getText().toString().trim().isEmpty()) {
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
        } else if(binding.dates.getText().toString().trim().isEmpty()) {
            binding.dates.setError((getString(R.string.please_enter_total)));
            binding.dates.requestFocus();
        } else if(binding.status.getText().toString().trim().isEmpty()) {
            binding.status.setError(getString(R.string.paid_or_not));
            binding.status.requestFocus();
        }else{
            binding.id.setError(null);
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

    private void fetchAndSetUser(String id) {
        mFetchUserService.getUser(id).enqueue(new Callback<Shizuka>() {
            @Override
            public void onResponse(Call<Shizuka> call, Response<Shizuka> response) {
                if (response.isSuccessful()) {
                    binding.updateUserBtn.setEnabled(true);
                    binding.name.setText(response.body().getName());
                    binding.mobile.setText(response.body().getMobile());
                    binding.street.setText(response.body().getStreet());
                    binding.total.setText(response.body().getTotal());
                    binding.dates.setText(response.body().getDates());
                    binding.status.setText(response.body().getStatus());

                } else {
                    // todo: this thing need to be fixed.
                    Toast.makeText(UpdateUserActivity.this, "No such id like this: " + id, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Shizuka> call, Throwable t) {
                t.printStackTrace();

                Toast.makeText(UpdateUserActivity.this, "oOps!, Something went wrong!", Toast.LENGTH_SHORT).show();
                binding.updateUserBtn.setEnabled(false);
            }
        });
    }
}