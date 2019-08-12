package com.uca.aerolineaapp.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uca.aerolineaapp.R;
import com.uca.aerolineaapp.models.Flight;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder>{
    private List<Flight> flights;
    private Context context;

    public FlightAdapter(List<Flight> flights, Context context) {
        this.flights = flights;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView flightNumber;
        private TextView agency;
        private TextView origin;
        private TextView destination;
        private TextView departure;
        private TextView arrive;
        public ViewHolder (View view){
            super (view);
            flightNumber = view.findViewById(R.id.flight_number);
            agency = view.findViewById(R.id.agency_name);
            origin = view.findViewById(R.id.origin);
            destination = view.findViewById(R.id.destination);
            departure = view.findViewById(R.id.departure_hour);
            arrive = view.findViewById(R.id.arrive_hour);
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
        //final Flight flight = flights.get(position);

        //holder.flightNumber.setText(flight.getFlightNumber());
        //holder.origin.setText(flight.getOrigin());
        //holder.destination.setText(flight.getDestination());
        //holder.departure.setText(flight.getFlightDeparture());
        //holder.arrive.setText(flight.getFlightArrive());

    }

    @Override
    public int getItemCount() {
        return flights.size();
    }


}