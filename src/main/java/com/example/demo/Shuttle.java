package com.example.demo;

import java.util.LinkedList;
import java.util.List;
import com.example.utils.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The Shuttle class represents a shuttle bus that can carry students to different target addresses.
 * It maintains a list of current passengers (students) and a list of target addresses.
 */
public class Shuttle {
    private String currentTargetAddress;
    private List<String> targets;// a queue
    private List<Student> studentlist;// a queue
    public int number;
    private Position currentLocation;

    private StudentRepository studentRepository;

    /** Usually,
    It has three status:
    * 1: waiting at the stop (waiting)
    * 2: riding
    * 3: returning
    */
    private Status status;

    public Shuttle(){
        targets = new LinkedList<>();
        studentlist = new LinkedList<>();
        status = Status.waiting;
        currentLocation = new Position(0,0);
        //the number of students
        number = 0;
    }

    public void addStudent(Student student){
        studentlist.add(student);
        number++;
        System.out.println(student.getFirstname()+" "+student.getLastname()+" gets on.");
    }

    public void addAddress(String address){
        if(targets.size() == 0){
            currentTargetAddress = address;
            status = Status.riding;
        }
        targets.add(address);
        System.out.println("Currenttargetaddress:"+ currentTargetAddress);
    }

    public boolean dropOff(){
        // return false when nobody here
        if(targets.isEmpty()) return false;

        targets.remove(0);
        studentlist.remove(0);
        number--;
        if(number > 0) currentTargetAddress = targets.get(0);
        else{//If there is no passenger on the shuttle:
            currentTargetAddress = null;
            status = Status.returning;
        }
        System.out.println("Current target address is"+ currentTargetAddress);
        return true;
    }

    public void init(){
        studentlist. clear();
        targets. clear();
        number = 0;
        status = Status.waiting;
        currentTargetAddress = null;
    }

    public String getCurrentTargetAddress() {
        return currentTargetAddress;
    }

    public List<Student> getStudentlist() {return studentlist; }

    public List<String> getTargets() {
        return targets;
    }

    public void setCurrentTargetAddress(String currentTargetAddress) {
        this.currentTargetAddress = currentTargetAddress;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCurrentLocation(Position currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
