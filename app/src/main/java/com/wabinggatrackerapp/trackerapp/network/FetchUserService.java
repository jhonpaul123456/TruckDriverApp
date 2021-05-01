package com.wabinggatrackerapp.trackerapp.network;

import com.wabinggatrackerapp.trackerapp.model.Shizuka;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FetchUserService {
    @FormUrlEncoded
    @POST("view_list.php")
    Call<Shizuka> getUser(
            @Field("id") String id
    );
}
