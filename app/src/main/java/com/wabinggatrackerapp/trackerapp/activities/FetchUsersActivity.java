package com.wabinggatrackerapp.trackerapp.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wabinggatrackerapp.trackerapp.R;
import com.wabinggatrackerapp.trackerapp.adapters.UsersAdapter;
import com.wabinggatrackerapp.trackerapp.databinding.ActivityFetchUsersBinding;
import com.wabinggatrackerapp.trackerapp.model.shizukas;
import com.wabinggatrackerapp.trackerapp.network.FetchUsersService;
import com.wabinggatrackerapp.trackerapp.parents.BigPapaActivity;

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

        mUserFetcherService.getUsers().enqueue(new Callback<shizukas>() {
            @Override
            public void onResponse(Call<shizukas> call, Response<shizukas> response) {
                mUsersAdapter = new UsersAdapter(response.body().getUsers(), FetchUsersActivity.this);
                binding.driversRecyclerView.setAdapter(mUsersAdapter);
            }

            @Override
            public void onFailure(Call<shizukas> call, Throwable t) {
                Toast.makeText(FetchUsersActivity.this, "Communication err!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}