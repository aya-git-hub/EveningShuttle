package com.example.controller;
import com.example.demo.*;
import com.example.service.*;
import com.example.utils.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.time.LocalTime;

@ComponentScan(basePackages = "com.example.service")

@RestController
public class StudentController {
    @Autowired
    private StudentShuttleService studentShuttleService;
    private Shuttle shuttle ;

    // default constructor
    StudentController(){
        shuttle = new Shuttle();
    }

    @GetMapping("/student/{SUID}")
    public Response<Student> getStudentByID(@PathVariable long SUID){
        return Response.newSuccess(studentShuttleService.getStudentById(SUID));
    }
//
    @GetMapping("/dropOff")
    public String dropOffStudent() {
        if(shuttle.dropOff())
            return "Student has been dropped off successfully.";
        else
            return "Warning, nobody is in the shuttle now.";
}
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/current")
    public String getCurrentTarget() {
        return "Current Target: " + shuttle.getCurrenttargetaddress();
    }

    @GetMapping("/studentList")//show current students in the shuttle
    public String getStudentList() {
        return "Student List: " + shuttle.getStudentlist().toString();
    }

    @GetMapping("/addPassenger")
    public Response<String>addPassenger(@RequestParam("SUID") long SUID, @RequestParam("address") String address){
        Student passenger = studentShuttleService.getStudentById(SUID);
        shuttle.addStudent(passenger);
        shuttle.addAddress(address);
        System.out.println(passenger.getSUID()+" gets on!");
        return Response.newSuccess("Yes");
    }

    @GetMapping("/requestPickupBySuid")
    public Response<EstimatedTimeArrival> addRequest(@RequestParam("SUID") long SUID,Model model){
        // Response<Boolean> response = new Response<>();
        // Send randomly ETA
        Random rand = new Random();
        int randomminute = rand.nextInt(60);
        int randomsecond = rand.nextInt(60);
        LocalTime time = LocalTime.of(0, randomminute, randomsecond);

        //Generate the ETA and request shuttle.
        EstimatedTimeArrival ETA = new EstimatedTimeArrival(studentShuttleService.addRequest(SUID),time);

        try { //Try to find it:
            studentShuttleService.requestPickup(SUID, ETA);
        } catch (InvalidUserException e) {
            System.out.println("Caught exception: " + e.getMessage());
            return Response.newFail("No such student: Access Denied");
        }
        //If it finds the student:
        return Response.newSuccess(ETA);
    }

    @PostMapping("/student")
    public Response<Long> addStudent(@RequestBody Student student){
        return Response.newSuccess(studentShuttleService.addStudent(student));
        // TODO: 2024/10/31
    }
}
