package com.uca.aerolineaapp.ui.fragments;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tumblr.remember.Remember;
import com.uca.aerolineaapp.R;
import com.uca.aerolineaapp.api.Api;
import com.uca.aerolineaapp.constants.Constants;
import com.uca.aerolineaapp.models.Flight;
import com.uca.aerolineaapp.ui.adapters.FlightAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton newComplaintButton;


    public HomeFragment() {
        // Required empty public constructor
    }

    //hacere un if de los vuelos y reservas en perfil
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Fresco.initialize(getContext());
        init(view);
        return view;
    }
    public void init(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_home);
        newComplaintButton = view.findViewById(R.id.fab_add_reserve);


        Call<List<Flight>> getFlights = Api.instance().getFlights(Remember.getString("access_token", ""));
        getFlights.enqueue(new Callback<List<Flight>>() {
            @Override
            public void onResponse(@NonNull Call<List<Flight>> call, @NonNull Response<List<Flight>> response) {
                if(response.code()==200){
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setHasFixedSize(true);
                    List<Flight> flights= response.body();
                    FlightAdapter flightAdapter = new FlightAdapter(flights, getContext());
                    recyclerView.setAdapter(flightAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Flight>> call, Throwable t) {
                Toast.makeText(getContext(), "Error flights call", Toast.LENGTH_SHORT).show();
            }
        });

       /*metodo para mandar a llamar en el api*/
    }


}
