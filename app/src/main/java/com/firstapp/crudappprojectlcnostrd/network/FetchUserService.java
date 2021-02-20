package com.firstapp.crudappprojectlcnostrd.network;

import com.firstapp.crudappprojectlcnostrd.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FetchUserService {
    @GET("fetch_user.php")
    Call<User> getUser(
            @Query("id") String id
    );
}
