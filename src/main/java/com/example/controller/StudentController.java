package com.example.controller;

import com.example.demo.Shuttle;
import com.example.demo.Student;
import com.example.service.Response;
import com.example.service.StudentShuttleService;
import com.example.utils.EstimatedTimeArrival;
import com.example.utils.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Random;

/**
 * Controllers play a crucial role in Spring Boot applications, as they handle incoming HTTP requests and determine the appropriate response to send back.
 * Act as intermediaries between the client (usually a web browser or a mobile app) and the application’s business logic.
 */
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
        return studentShuttleService.dropStudent(shuttle);
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/current")
    public String getCurrentTarget() {
        return "Current Target: " + shuttle.getCurrentTargetAddress();
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
        // Generate the ETA and request shuttle.
        // Use addRequest() to determine whether the student is valid.
        EstimatedTimeArrival ETA = new EstimatedTimeArrival(studentShuttleService.addRequest(SUID),time);
        try { //Try to find it:
             //If the student did not make the request:
            studentShuttleService.requestPickup(SUID, ETA);
            //else return Response.newFail("This student has already submitted the request and cannot submit it again. ");
        } catch (InvalidUserException e) {
            System.out.println("Caught exception: " + e.getMessage());
            return Response.newFail(e.getMessage());
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
