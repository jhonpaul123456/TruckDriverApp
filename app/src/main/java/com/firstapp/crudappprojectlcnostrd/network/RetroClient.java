package com.firstapp.crudappprojectlcnostrd.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    public static final String BASE_URL = "http://192.168.1.18:8080/CrudAppProjectLcnoStrdAPIS/";
    public static final String BASE_URL_WITHOUT_ENDING_SLASH = RetroClient.BASE_URL.substring(0, RetroClient.BASE_URL.length() - 1);

    private final Retrofit mRetrofit;

    public RetroClient() {
        this.mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
