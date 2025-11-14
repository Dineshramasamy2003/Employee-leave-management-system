package com.example.employees.controller;

import com.example.employees.model.Employee;
import com.example.employees.service.AdminService;
import com.example.employees.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class EmpController {

    @Autowired
    private EmpService empService;


    @PostMapping("add/emp")
    public String register(@RequestBody Employee employee){
        return empService.register(employee);
    }

    @PostMapping("leave/apply")
    public String leaveapply(@RequestBody Employee employee){
        return empService.leaveapply(employee);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Employee employee) {
        return empService.login(employee);
    }
    @PostMapping("/empdetail")
    public ResponseEntity<Employee>show(@RequestBody Employee employee){
        return empService.show(employee);
    }

    @PostMapping("leave/history")
    public ResponseEntity<Employee> getLeaveHistory(@RequestBody Employee employee) {
        Employee dbUser = empService.getLeaveHistory(employee.getEmail());

        if (dbUser != null) {
            return ResponseEntity.ok(dbUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
