package com.example.employees.controller;

import com.example.employees.model.Employee;
import com.example.employees.service.AdminService;
import com.example.employees.service.EmpService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class AdminController {

    @Autowired
    private AdminService adminService;

@PostMapping("adpage")
public ResponseEntity<List<Employee>> admin(@RequestBody Employee employee) {
    return adminService.display(employee);
}
    @PutMapping("/adaccess/{id}")
    public ResponseEntity<String> approve(@PathVariable int id) {
        adminService.approve(id);
        return ResponseEntity.ok("Leave approved successfully!");
    }
    @PutMapping("/adreject/{id}")
    public ResponseEntity<String> reject(@PathVariable int id) {
        adminService.reject(id);
        return ResponseEntity.ok("Leave Rejected successfully!");
    }



}
