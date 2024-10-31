package com.example.service;

import com.example.demo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;

import com.example.utils.*;

@Service
public class StudentShuttleService implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    private final RestTemplate restTemplate;

    StudentShuttleService(){
       restTemplate = new RestTemplate();
    }

    public String sendHttpRequest(String url) {
        try {
            System.out.println("Sending request to URL: " + url);
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            System.out.println("Response status code: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                System.out.println("Yes");
                return "Yes";
            } else {
                System.out.println("No");
                return "No";
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            return "No";
        }
    }

    public String requestPickup(long suid, EstimatedTimeArrival eta) throws InvalidUserException{
        if (eta.isValid()) {
            List<Student> studentlist = studentRepository.findBySUID(suid);
            if (true) {
                // Student found, update the record
                Student student = studentlist.get(0);
                student.setEdt(eta.getEta());
                studentRepository.save(student);
                System.out.println("Student record updated successfully.");
            }
            return "yes";
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
