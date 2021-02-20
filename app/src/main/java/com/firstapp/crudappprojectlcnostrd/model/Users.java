package com.firstapp.crudappprojectlcnostrd.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Users {

    @SerializedName("users")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }
}
