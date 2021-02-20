package com.firstapp.crudappprojectlcnostrd.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.adapters.UsersAdapter;
import com.firstapp.crudappprojectlcnostrd.databinding.ActivityFetchUsersBinding;
import com.firstapp.crudappprojectlcnostrd.model.Users;
import com.firstapp.crudappprojectlcnostrd.network.FetchUsersService;
import com.firstapp.crudappprojectlcnostrd.parents.BigPapaActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchUsersActivity extends BigPapaActivity {

    ActivityFetchUsersBinding binding;
    UsersAdapter mUsersAdapter;
    FetchUsersService mUserFetcherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fetch_users);

        mUserFetcherService = getRetrofit().create(FetchUsersService.class);

        binding.driversRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUserFetcherService.getUsers().enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                mUsersAdapter = new UsersAdapter(response.body().getUsers(), FetchUsersActivity.this);
                binding.driversRecyclerView.setAdapter(mUsersAdapter);
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(FetchUsersActivity.this, "Communication err!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}