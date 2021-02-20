package com.firstapp.crudappprojectlcnostrd.activities;

import android.os.Bundle;
import android.widget.Toast;

import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.model.User;
import com.firstapp.crudappprojectlcnostrd.network.CreateUserService;
import com.firstapp.crudappprojectlcnostrd.parents.BigPapaActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserActivity extends BigPapaActivity {

    CreateUserService mCreateUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        mCreateUserService = getRetrofit().create(CreateUserService.class);

        Toast.makeText(this, "Request Executed.", Toast.LENGTH_SHORT).show();

        mCreateUserService.createUser("Test Driver",
                "test plate", "test condition", "test_type ")
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(CreateUserActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(CreateUserActivity.this, "Communication err", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}