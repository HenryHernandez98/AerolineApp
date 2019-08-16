package com.uca.aerolineaapp.api;

import com.uca.aerolineaapp.models.AccessToken;
import com.uca.aerolineaapp.models.Airline;
import com.uca.aerolineaapp.models.Flight;
import com.uca.aerolineaapp.models.Identity;
import com.uca.aerolineaapp.models.Login;
import com.uca.aerolineaapp.models.LoginRequest;
import com.uca.aerolineaapp.models.User;

import java.util.List;

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

    @POST("Flights")
    Call<Flight> saveFlight (@Body Flight flight);

    @GET("Flight")
    Call<List<Flight>> getFlihts(String auth);

    @POST("Airlines")
    Call<Airline> saveAirline (@Body Airline airline);

    @GET("Airlines")
    Call<List<Airline>> getAirlines(String auth);
}
