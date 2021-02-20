package com.firstapp.crudappprojectlcnostrd.network;

import com.firstapp.crudappprojectlcnostrd.model.Users;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FetchUsersService {
    @GET("users_list.php")
    Call<Users> getUsers();
}
