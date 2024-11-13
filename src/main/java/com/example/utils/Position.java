package com.example.utils;

public class Position {
    protected double longitude;
    protected double latitude;
    public Position(double longitude, double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String toString(){
        return "longitude: "+longitude+", latitude: "+latitude;
    }
}
