package com.example.utils;
import java.util.Random;

/**
 *  If you only want to generate a random position
 *  Use this!
 */
public class RandomPosition extends Position{
    // DONE: 2024/11/5
    public RandomPosition(){
        super((new Random().nextDouble() * 360) - 180, (new Random().nextDouble() * 180) - 90);
    }
    public RandomPosition(double a, double b ){
        super( a, b);
    }
    @Override
    public String toString() {
        String realLongitude = longitude >= 0 ? String.format("%.2f", longitude)+"°E" : String.format("%.2f", -longitude)+"°W";
        String realLatitude = latitude >= 0 ? String.format("%.2f", latitude)+"°N" : String.format("%.2f", -latitude)+"°S";
        return "Longitude: "+realLongitude+", Latitude: "+realLatitude;
    }
}
