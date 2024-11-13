package com.example.utils;

/**
 * The three status that the shuttle will be.
 */
public enum Status{
    // When the shuttle is waiting at the stop
    waiting(),
    // Riding or prepare for riding, which means when the first student gets on, the status will become riding
    riding(),
    // When the shuttle drops off the last student, it will return to the bus stop.
    returning();
}
