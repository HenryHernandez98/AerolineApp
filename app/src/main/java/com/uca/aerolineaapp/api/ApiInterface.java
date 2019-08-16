package com.uca.aerolineaapp.api;

import com.uca.aerolineaapp.models.AccessToken;
import com.uca.aerolineaapp.models.Identity;
import com.uca.aerolineaapp.models.Login;
import com.uca.aerolineaapp.models.LoginRequest;
import com.uca.aerolineaapp.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {



    @GET("Logins/Autenticate")
    boolean isRunning();

    @POST("Logins/Authenticate")
    Call<String> token(@Body Login user);

    @POST("Users")
    Call<User> saveUser (@Body User user);

    @POST("Identities")
    Call<Identity> saveIdentities (@Body Identity identity);

    @POST("SignUp")
    Call<Login> saveLogin (@Body Login login);
}
