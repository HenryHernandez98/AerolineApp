package com.uca.aerolineaapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.uca.aerolineaapp.R;
import com.uca.aerolineaapp.api.Api;
import com.uca.aerolineaapp.models.Airline;
import com.uca.aerolineaapp.models.Flight;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightActivity extends AppCompatActivity {

    private EditText departure;
    private EditText destination;
    private EditText flightNumber;
    private EditText boardingTime;
    private EditText gate;
    private EditText zone;
    private EditText layover;
    private EditText departDateTime;
    private EditText arriveDateTime;
    private String availability;
    private EditText capacity;
    private Spinner airlineName;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        initViews();
        initAction();
        fillSpinnerAirlines();
    }

    public void initViews(){
        departure = findViewById(R.id.edit_text_departure);
        destination = findViewById(R.id.edit_text_destination);
        flightNumber = findViewById(R.id.edit_text_flight_number);
        boardingTime = findViewById(R.id.edit_text_boarding_time);
        gate = findViewById(R.id.edit_text_gate);
        zone = findViewById(R.id.edit_text_zone);
        layover = findViewById(R.id.edit_text_layover);
        departDateTime = findViewById(R.id.edit_text_depart_time);
        arriveDateTime = findViewById(R.id.edit_text_arrive_time);
        availability = "Disponible";
        capacity = findViewById(R.id.edit_text_capacity);
        airlineName = findViewById(R.id.spinner_airlines);
        create = findViewById(R.id.button_create_flight);
    }

    public void initAction(){
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postFlight();
            }
        });
    }

    public void postFlight(){
        String Departure = departure.getText().toString();
        String Destination = destination.getText().toString();
        String FlightNumber = flightNumber.getText().toString();
        String BoardingTime = boardingTime.getText().toString();
        String Gate = gate.getText().toString();
        String Zone = zone.getText().toString();
        String Layover = layover.getText().toString();
        String DepartDateTime = departDateTime.getText().toString();
        String ArriveDateTime = arriveDateTime.getText().toString();
        String Capacity = capacity.getText().toString();
        String AirlineName = airlineName.toString();

        if(Departure.equals(" ")||Destination.equals(" ")|| FlightNumber.equals(" ")|| BoardingTime.equals(" ")|| Gate.equals(" ")||
                Zone.equals(" ")|| Layover.equals(" ")|| DepartDateTime.equals(" ")|| ArriveDateTime.equals(" ")|| Capacity.equals(" ")||
                AirlineName.equals(" ")){
            Toast.makeText(getApplicationContext(),"No pueden haber campos vacios",Toast.LENGTH_SHORT).show();
        } else {
            final Flight flight = new Flight();

            flight.setDeparture(departure.getText().toString());
            flight.setDestination(destination.getText().toString());
            flight.setFlightNumber(flightNumber.getText().toString());
            flight.setBoardingTime(boardingTime.getText().toString());
            flight.setGate(gate.getText().toString());
            flight.setZone(zone.getText().toString());
            flight.setDepartDateTime(departDateTime.getText().toString());
            flight.setArriveDateTime(arriveDateTime.getText().toString());
            flight.setAvailability(availability);
            flight.setCapacity(Integer.parseInt(capacity.getText().toString()));
            flight.setIdAirline(idSpinner());

            Call<Flight> flightCall = Api.instance().saveFlight(flight);
            flightCall.enqueue(new Callback<Flight>() {
                @Override
                public void onResponse(Call<Flight> call, Response<Flight> response) {
                    if (response.body()!= null) {
                        flight.setDeparture(departure.getText().toString());
                        flight.setDestination(destination.getText().toString());
                        flight.setFlightNumber(flightNumber.getText().toString());
                        flight.setBoardingTime(boardingTime.getText().toString());
                        flight.setGate(gate.getText().toString());
                        flight.setZone(zone.getText().toString());
                        flight.setDepartDateTime(departDateTime.getText().toString());
                        flight.setArriveDateTime(arriveDateTime.getText().toString());
                        flight.setAvailability(availability);
                        flight.setCapacity(Integer.parseInt(capacity.getText().toString()));
                        flight.setIdAirline(idSpinner());
                        Toast.makeText(getApplicationContext(), "Se registró correctamente el vuelo", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error al registrar el vuelo", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Flight> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error al registrar el vuelo", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void fillSpinnerAirlines(){

    }

    public int idSpinner(){
        int id = 0;
        return id;
    }
}