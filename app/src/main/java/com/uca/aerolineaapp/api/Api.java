package com.uca.aerolineaapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {


    private final static String Url = "http://192.168.43.2:7080/api";

    private static String getBase(){
        return Url + "/";
    }

    public static ApiInterface instance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.getBase())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiInterface.class);
    }

}
