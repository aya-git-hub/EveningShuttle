package com.example.service;

import com.example.demo.*;
import com.example.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@Service
public class StudentShuttleService implements StudentService{

    @Autowired
    private StudentRepository studentRepository;
    private final RestTemplate restTemplate;

    StudentShuttleService(){
       restTemplate = new RestTemplate();
    }

    @Override
    @Scheduled(fixedRate = 20000) // per second
    public void sendShuttleLocation() {
        Position randomlocation = new RandomPosition();
        //String url = "http://localhost:5000/shuttleLocation?longitude=12.3&latitude=34.2";
        String url = "http://localhost:5000/shuttleLocation?longitude=" + randomlocation.getLongitude() + "&latitude=" + randomlocation.getLatitude();
        System.out.println("hello");
        try {
            restTemplate.getForObject(url, String.class);
            System.out.println("Location has been sent"+randomlocation);
        } catch (Exception e) {
            System.out.println("Fail to send location: " + e.getMessage());
        }
    }

    public String requestPickup(long suid, EstimatedTimeArrival eta) throws InvalidUserException{
        if (eta.isValid()) {
            // Student found, update the record
            List<Student> studentlist = studentRepository.findBySUID(suid);
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
}
