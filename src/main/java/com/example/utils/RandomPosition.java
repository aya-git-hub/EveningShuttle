package com.example.utils;
import java.util.Random;

/**
 *  If you only want to generate a random position
 *  Use this!
 */
public class RandomPosition extends Position{

    public RandomPosition(){
        super((new Random().nextDouble() * 360) - 180, (new Random().nextDouble() * 180) - 90);
    }
    public RandomPosition(double a, double b ){
        super(a,b);
    }
    @Override
    public String toString() {
        String reallongitude = longitude >= 0 ? String.format("%.2f", longitude)+"°E" : String.format("%.2f", -longitude)+"°W";
        String reallatitude = latitude >= 0 ? String.format("%.2f", latitude)+"°N" : String.format("%.2f", -latitude)+"°S";
        return "Longitude: "+reallongitude+", Latitude: "+reallatitude;
    }
}
