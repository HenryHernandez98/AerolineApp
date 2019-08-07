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
    private List<Flight> flight;
    private Context context;

    public FlightAdapter(List<Flight> complaint, Context context) {
        this.flight = complaint;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView test;/*Este se va a borrar*/
        public ViewHolder (View view){
            super (view);
            test = view.findViewById(R.id.test);


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
        final Flight post = flight.get(position);

    }

    @Override
    public int getItemCount() {
        return flight.size();
    }


}