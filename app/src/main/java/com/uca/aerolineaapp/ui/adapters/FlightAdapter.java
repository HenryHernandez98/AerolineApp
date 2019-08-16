package com.uca.aerolineaapp.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.uca.aerolineaapp.R;
import com.uca.aerolineaapp.models.Airline;
import com.uca.aerolineaapp.models.Flight;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder>{
    private List<Flight> flights;
    private List<Airline> airlines;
    private Context context;

    public FlightAdapter(List<Flight> flights, Context context, List<Airline> airlines) {
        this.flights = flights;
        this.context = context;
        this.airlines = airlines;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView flightNumber;
        private TextView airline;
        private TextView origin;
        private TextView destination;
        private TextView departure;
        private TextView arrive;
        ViewHolder(View view){
            super (view);
            flightNumber = view.findViewById(R.id.flight_number);
            origin = view.findViewById(R.id.origin);
            destination = view.findViewById(R.id.destination);
            departure = view.findViewById(R.id.departure_hour);
            arrive = view.findViewById(R.id.arrive_hour);
            airline = view.findViewById(R.id.airline_name);
        }

    }

    @NonNull
    @Override
    public FlightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_flight, parent, false);
        return new FlightAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FlightAdapter.ViewHolder holder, final int position) {
        final Flight flight = flights.get(position);
        for(int i=0; i<airlines.size(); i++){
            if(airlines.get(i).getIdAirline()==flight.getIdAirline()){
                holder.airline.setText(airlines.get(i).getName());
            }
        }

        try{
            holder.flightNumber.setText(flight.getFlightNumber());
            holder.origin.setText(flight.getDeparture());
            holder.destination.setText(flight.getDestination());
            holder.departure.setText(flight.getDepartDateTime());
            holder.arrive.setText(flight.getArriveDateTime());
        }
        catch (Exception e){
            Toast.makeText(context, "Error al mostrar vuelos", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return flights.size();
    }


}