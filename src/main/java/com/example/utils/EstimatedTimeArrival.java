package com.example.utils;

import java.time.LocalTime;

public class EstimatedTimeArrival {
    private boolean isValid;
    private LocalTime eta;
    public EstimatedTimeArrival(boolean v, LocalTime t){
        this.isValid = v;
        this.eta = t;
    }

    public boolean isValid() {
        return isValid;
    }

    public LocalTime getEta() {
        return eta;
    }
}
