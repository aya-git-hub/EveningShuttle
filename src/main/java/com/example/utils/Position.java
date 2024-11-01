package com.example.utils;

public class Position {
    private double longitude;
    private double latitude;
    public Position(double long_, double lat){
        longitude = long_;
        latitude = lat;
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
