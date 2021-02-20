package com.firstapp.crudappprojectlcnostrd.network;

import com.firstapp.crudappprojectlcnostrd.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CreateUserService {
    @FormUrlEncoded
    @POST("create_user.php")
    Call<User> createUser(
            @Field("driver_name") String driverName,
            @Field("plate_number") String plateNumber,
            @Field("truck_condition") String condition,
            @Field("truck_type") String truck_type
    );
}
