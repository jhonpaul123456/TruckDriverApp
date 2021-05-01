package com.wabinggatrackerapp.trackerapp.network;

import com.wabinggatrackerapp.trackerapp.model.Shizuka;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DeleteUserService {
    @FormUrlEncoded
    @POST("delete_user.php")
    Call<Shizuka> deleteUser(
            @Field("id") String id
    );
}
