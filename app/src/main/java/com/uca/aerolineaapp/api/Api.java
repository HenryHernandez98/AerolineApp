package com.uca.aerolineaapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {


    private final static String Url = "nuestra.api.xd";

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
