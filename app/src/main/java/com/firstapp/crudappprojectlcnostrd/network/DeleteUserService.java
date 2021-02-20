package com.firstapp.crudappprojectlcnostrd.network;

import com.firstapp.crudappprojectlcnostrd.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DeleteUserService {
    @FormUrlEncoded
    @POST("delete_user.php")
    Call<User> deleteUser(
            @Field("id") String id
    );
}
