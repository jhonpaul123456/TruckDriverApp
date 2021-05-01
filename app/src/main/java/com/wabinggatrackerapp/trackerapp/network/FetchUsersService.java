package com.wabinggatrackerapp.trackerapp.network;

import com.wabinggatrackerapp.trackerapp.model.shizukas;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FetchUsersService {
    @GET("cos_list.php")
    Call<shizukas> getUsers();
}
