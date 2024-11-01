package com.example.demo;

import java.util.LinkedList;
import java.util.List;
import com.example.utils.*;

/**
 * The Shuttle class represents a shuttle bus that can carry students to different target addresses.
 * It maintains a list of current passengers (students) and a list of target addresses.
 */

public class Shuttle {
    //@Column(name = "CurrentTargetAddress")
    private String currenttargetaddress;
    private List<String> targets;
    private List<Student> studentlist;
    private int number;

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
            currenttargetaddress = address;
            status = Status.riding;
        }

        targets.add(address);
        System.out.println("Currenttargetaddress:"+currenttargetaddress);
    }

    public void dropOff(){
        targets.remove(0);
        studentlist.remove(0);
        number--;
        if(number > 0) currenttargetaddress = targets.get(0);
        else{//If there is no passenger on the shuttle:
            currenttargetaddress = null;
            status = Status.returning;

        }
        System.out.println("currenttargetaddress"+currenttargetaddress);
    }

}
