package com.uca.aerolineaapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uca.aerolineaapp.R;
import com.uca.aerolineaapp.api.Api;
import com.uca.aerolineaapp.models.Airline;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirlineActivity extends AppCompatActivity {

    private EditText airlineName;
    private EditText desc;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_airline);
        initViews();
        initAction();
    }

    public void initViews(){
        airlineName = findViewById(R.id.edit_text_agency_name);
        desc = findViewById(R.id.edit_text_description);
        create = findViewById(R.id.button_create_agency);
    }


    public void initAction(){
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postAgency();
                blankFields();
            }
        });
    }

    public void postAgency(){
        String Airline = airlineName.getText().toString();
        String Desc = desc.getText().toString();

        if(Airline.equals(" ")|| Desc.equals(" ")){
            Toast.makeText(getApplicationContext(),"No pueden haber campos vacios",Toast.LENGTH_SHORT).show();
        } else {
            final Airline airline = new Airline();

            airline.setName(airlineName.getText().toString());
            airline.setDescription(desc.getText().toString());

            Call<Airline> airlineCall = Api.instance().saveAirline(airline);
            airlineCall.enqueue(new Callback<com.uca.aerolineaapp.models.Airline>() {
                @Override
                public void onResponse(Call<com.uca.aerolineaapp.models.Airline> call, Response<com.uca.aerolineaapp.models.Airline> response) {
                    if (response.body()!= null) {
                        airline.setIdAirline(response.body().getIdAirline());
                        airline.setName(airlineName.getText().toString());
                        airline.setDescription(desc.getText().toString());

                        Toast.makeText(getApplicationContext(), "Se registró correctamente la Aerolinea", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<com.uca.aerolineaapp.models.Airline> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error al crear Aerolinea", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void blankFields(){
        airlineName.setText("");
        desc.setText("");
    }
}
