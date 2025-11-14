package com.example.employees.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String name;
    private String phonenumber;
    @Column(columnDefinition = "varchar(20) default 'NA'")
    private String leavestatus="NA";

    @Column(columnDefinition = "varchar(20) default 'NA'")
    private String applydate= "NA";
    @Column(columnDefinition = "varchar(200) default 'NA'")
    private String reason="NA";
    public Employee(String email, String password, String name, String phonenumber,String leavestatus) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phonenumber = phonenumber;
        this.leavestatus = leavestatus;
    }
}
