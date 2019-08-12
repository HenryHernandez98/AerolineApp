package com.uca.aerolineaapp.models;

public class Booking_Flight {

    private int idBooking_Flight;
    private int idBooking;
    private int idFlight;

    public int getIdBooking_Flight() {
        return idBooking_Flight;
    }

    public void setIdBooking_Flight(int idBooking_Flight) {
        this.idBooking_Flight = idBooking_Flight;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }
}
