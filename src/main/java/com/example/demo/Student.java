package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalTime;

/**
 * The Student class represents a student entity that maps to the "dmo.Student" table in an SQL Server database.
 * This entity is part of a Java application using JPA (see StudentRepository).
 * The fields of this class are mapped to the columns of the "Student" table.
 *
 * "@Column" are used to define the relationship between this Java class and the real table column.
 */

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

    public LocalTime getEta() {
        return eta;
    }

    /*-------------- to set -----------*/
    public void setEdt(LocalTime eta) {
        this.eta = eta;
    }

    @Override
    public String toString() {
        return "SUID:"+SUID+" "+"Name:"+firstname+" "+lastname;
    }

}
