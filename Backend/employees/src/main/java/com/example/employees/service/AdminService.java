package com.example.employees.service;

import com.example.employees.model.Employee;
import com.example.employees.repository.EmpRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private EmpRepository empRepository;

    public ResponseEntity<List<Employee>> display(Employee employee) {
        if (employee.getEmail().equals("admin@gmail.com") && employee.getPassword().equals("admin@1234")) {
            List<Employee> allEmployees = empRepository.findAll();
            return ResponseEntity.ok(allEmployees);
        }
        return ResponseEntity.status(401).body(null);
    }

    public void approve(int id) {
        Employee dbuser = empRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));

        dbuser.setLeavestatus("Approved");
        empRepository.save(dbuser);
    }
    public void reject(int id) {
        Employee dbuser = empRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));

        dbuser.setLeavestatus("Rejected");
        empRepository.save(dbuser);
    }


}
