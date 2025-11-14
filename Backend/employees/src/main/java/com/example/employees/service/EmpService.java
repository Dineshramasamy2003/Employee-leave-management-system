package com.example.employees.service;

import com.example.employees.model.Employee;
import com.example.employees.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmpService {

    @Autowired
    private EmpRepository empRepository;
    BCryptPasswordEncoder PasswordEncoder=new BCryptPasswordEncoder(12);

    public String register(Employee employee){
        if (empRepository.existsByEmail(employee.getEmail())) {
            return "Email id already exists!";
        }
        employee.setPassword(PasswordEncoder.encode(employee.getPassword()));
        empRepository.save(employee);
        return "saved";

    }
    public ResponseEntity<String> login(Employee employee){
        if (!empRepository.existsByEmail(employee.getEmail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

        }
        Employee dbUser = empRepository.getByEmail(employee.getEmail());
        if (PasswordEncoder.matches(employee.getPassword(), dbUser.getPassword())) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
//        if(employee.getPassword().equals(dbUser.getPassword())) {
//            return ResponseEntity.ok("Login successful!");
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

    }

    public ResponseEntity<Employee> show( Employee employee) {
        Employee dbUser = empRepository.getByEmail(employee.getEmail());

        if (dbUser != null) {
            return ResponseEntity.ok(dbUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public String leaveapply(Employee employee){
        if (!empRepository.existsByEmail(employee.getEmail())) {
            return "Email id is not found";
        }
        Employee dbUser = empRepository.getByEmail(employee.getEmail());
                //if (dbUser.getLeavestatus().equals("NA")) {
                    dbUser.setApplydate(employee.getApplydate());
                    dbUser.setReason(employee.getReason());
                    dbUser.setLeavestatus("Pending");
                    empRepository.save(dbUser);
                    return "Leave is applied";

               // }
                //return "Leave is Already applied";

    }
    public Employee getLeaveHistory(String email) {
        return empRepository.getByEmail(email);
    }


}
