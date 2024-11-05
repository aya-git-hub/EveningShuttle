package com.example.service;

import com.example.demo.Student;
import com.example.utils.EstimatedTimeArrival;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *  Service interface
 *
 */
public interface StudentService {
    void sendShuttleLocation();
    String requestPickup(long suid, EstimatedTimeArrival eta);
    long addStudent(@RequestBody Student student);
     Student getStudentById(long id);
    public boolean addRequest(@RequestBody long id);
}
