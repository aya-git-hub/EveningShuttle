package com.example.service;

import com.example.demo.Shuttle;
import com.example.demo.Student;
import com.example.demo.StudentRepository;
import com.example.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Service layer is a fundamental concept in Spring Boot applications.
 * It represents a layer of your application responsible for carrying out business logic and encapsulating the application’s functionality.
 * Services are typically stateless and are designed to perform specific tasks.
 */
@Service
public class StudentShuttleService implements StudentService{

    @Autowired
    private StudentRepository studentRepository;
    private final RestTemplate restTemplate;

    public String dropStudent(Shuttle shuttle){
        // FIXME: 2024/11/09
        if(shuttle.getStudentlist().isEmpty()) return "Warning, nobody is in the shuttle now.";
        studentRepository.dropHim(shuttle.getStudentlist().get(0).getSUID());
        shuttle.getTargets().remove(0);
        shuttle.getStudentlist().remove(0);
        shuttle.number--;
        if(shuttle.number > 0) shuttle.setCurrentTargetAddress(shuttle.getTargets().get(0));
        else {//If there is no passenger on the shuttle:
            shuttle.setCurrentTargetAddress(null);
            shuttle.setStatus(Status.returning);
        }
        System.out.println("Current target address is"+ shuttle.getCurrentTargetAddress());
        return "Student has been dropped off successfully.";
        // DONE: 2024/11/13  
    }

    StudentShuttleService(){
       restTemplate = new RestTemplate();
    }

    @Override
    @Scheduled(fixedRate = 20000) // per second
    public void sendShuttleLocation() {
        // DONE: 2024/11/5
        Position randomLocation = new RandomPosition();
        //String url = "http://localhost:5000/shuttleLocation?longitude=0&latitude=0";
        String url = "http://localhost:5000/shuttleLocation?longitude=" + randomLocation.getLongitude() + "&latitude=" + randomLocation.getLatitude();
        System.out.println("Hello, sent!");
        try {
            restTemplate.getForObject(url, String.class);
            System.out.println("Location has been sent: "+randomLocation);
        } catch (Exception e) {
            System.out.println("Fail to send location: " + e.getMessage());
        }
    }

    public String requestPickup(long suid, EstimatedTimeArrival eta) throws InvalidUserException{
        if (eta.isValid()) {
            // Student found, update the record
            List<Student> studentlist = studentRepository.findBySUID(suid);
            if(this.ifRequest(suid))
                throw new InvalidUserException("This student has already submitted the request and cannot submit it again.");
            Student student = studentlist.get(0);
            student.setEdt(eta.getEta());
            studentRepository.save(student);
            System.out.println("Student record updated successfully.");
            return "index";
        } else {
            System.out.println("no such student");
            throw new InvalidUserException("No such student: Access Denied");
        }
    }

    public Student getStudentById(long id){
       return studentRepository.findById(id).orElseThrow(RuntimeException::new);
   }

    public boolean addRequest(@RequestBody long id){
        return studentRepository.existsById(id);
    }

    public long addStudent(@RequestBody Student student){
        List<Student> studentlist = studentRepository.findBySUID(student.getSUID());
        if(!CollectionUtils.isEmpty(studentlist))
            throw new IllegalMonitorStateException("SUID: "+student.getSUID()+"has been taken!");
        Student newstu = studentRepository.save(student);
        return newstu.getSUID();
        // TODO: 2024/10/31
    }

    //If this student has already submitted the request.
    public boolean ifRequest(long SUID) {
        List<Student> studentList = studentRepository.findBySUID(SUID);
        if (studentList.isEmpty()) {
            throw new IllegalMonitorStateException("SUID: " + SUID + " not found!");
        }
        Student student = studentList.get(0);
        return !(student.getEta() == null);
        /**
         -> true:  ETA is null , student did not submit the request.
         -> false: ETA is not null, student  submitted the request.
         */
    }
}
