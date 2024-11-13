package com.example.utils;

import java.time.LocalTime;

public class EstimatedTimeArrival implements Comparable<EstimatedTimeArrival> {
    private boolean isValid;
    private LocalTime eta;
    public EstimatedTimeArrival(boolean v, LocalTime t){
        this.isValid = v;
        this.eta = t;
    }
    @Override
    public int compareTo(EstimatedTimeArrival other) {
       return this.eta.compareTo(other.eta);
    }
    public boolean isValid() {
        return isValid;
    }

    public LocalTime getEta() {
        return eta;
    }
}
