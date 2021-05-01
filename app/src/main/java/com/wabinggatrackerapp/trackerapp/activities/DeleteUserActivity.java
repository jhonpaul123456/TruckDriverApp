package com.wabinggatrackerapp.trackerapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.wabinggatrackerapp.trackerapp.R;
import com.wabinggatrackerapp.trackerapp.databinding.ActivityDeleteUserBinding;
import com.wabinggatrackerapp.trackerapp.model.Shizuka;
import com.wabinggatrackerapp.trackerapp.network.DeleteUserService;
import com.wabinggatrackerapp.trackerapp.parents.BigPapaActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteUserActivity extends BigPapaActivity {

    ActivityDeleteUserBinding binding;
    DeleteUserService mDeleteUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_delete_user);

        mDeleteUserService = getRetrofit().create(DeleteUserService.class);

        binding.deleteBtn.setOnClickListener(v -> {
            if (binding.id.getText().toString().trim().isEmpty()) {
                binding.id.setError(getString(R.string.please_enter_id));
                binding.id.requestFocus();
            } else {
                binding.id.setError(null);
                mDeleteUserService.deleteUser(binding.id.getText().toString().trim()).enqueue(new Callback<Shizuka>() {
                    @Override
                    public void onResponse(Call<Shizuka> call, Response<Shizuka> response) {
                        if (response.body().isStatusSuccess()) {
                            startActivity(new Intent(DeleteUserActivity.this, FetchUsersActivity.class));
                            Toast.makeText(DeleteUserActivity.this, "Successfully Deleted !", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(DeleteUserActivity.this, "Record not deleted !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Shizuka> call, Throwable t) {
                        Toast.makeText(DeleteUserActivity.this, "Server Error !", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }
        });

    }
}