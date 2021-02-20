package com.firstapp.crudappprojectlcnostrd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.databinding.ActivityDeleteUserBinding;
import com.firstapp.crudappprojectlcnostrd.model.User;
import com.firstapp.crudappprojectlcnostrd.network.DeleteUserService;
import com.firstapp.crudappprojectlcnostrd.parents.BigPapaActivity;

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
                mDeleteUserService.deleteUser(binding.id.getText().toString().trim()).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body().isStatusSuccess()) {
                            startActivity(new Intent(DeleteUserActivity.this, FetchUsersActivity.class));
                            Toast.makeText(DeleteUserActivity.this, "Successfully Deleted !", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(DeleteUserActivity.this, "Record not deleted !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(DeleteUserActivity.this, "Server Error !", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }
        });

    }
}