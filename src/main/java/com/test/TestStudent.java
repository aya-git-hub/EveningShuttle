package com.test;

import jakarta.persistence.*;
import jdk.jfr.Enabled;


@Table(name="student")
public class TestStudent {



    @Column(name = "Name")
    private String name;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
}
