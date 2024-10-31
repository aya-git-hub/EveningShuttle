package com.example.demo;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "Student")
public class Student {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id // Primary key
    private long SUID;

    //Student.firstname maps to dbo.Student.FirstName
    @Column(name = "FirstName")
    private String firstname;

    @Column(name = "LastName")
    private String lastname;

    @Column(name = "PhoneNumber")
    private String phonenumber;

    @Column(name = "ETA")
    private LocalTime eta;

    /*-------------- to get -----------*/
    public long getSUID() {
        return SUID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public LocalTime getEdt() { return eta; }

    /*-------------- to set -----------*/
    public void setEdt(LocalTime eta) {
        this.eta = eta;
    }

    @Override
    public String toString() {
        return "SUID:"+SUID+" "+"Name:"+firstname+" "+lastname;
    }
}
