package com.wabinggatrackerapp.trackerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class shizukas {

    @SerializedName("users")
    private List<Shizuka> shizukas;

    public List<Shizuka> getUsers() {
        return shizukas;
    }

    @Override
    public String toString() {
        return "shizukas{" +
                "shizukas=" + shizukas +
                '}';
    }

}
