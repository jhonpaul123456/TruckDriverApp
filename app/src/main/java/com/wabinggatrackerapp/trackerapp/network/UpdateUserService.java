package com.wabinggatrackerapp.trackerapp.network;

import com.wabinggatrackerapp.trackerapp.model.Shizuka;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UpdateUserService {
    @FormUrlEncoded
    @POST("update_user.php")
    Call<Shizuka> updateUser(
            @Field("id") String id,
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("street") String street,
            @Field("total") String total,
            @Field("dates") String dates,
            @Field("status") String status

    );
}
