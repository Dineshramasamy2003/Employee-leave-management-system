package com.example.employees.repository;

import com.example.employees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface EmpRepository extends JpaRepository<Employee,Integer> {
    Employee getByEmail(String email);
    Employee findByEmail(String email);
    boolean existsByEmail(String email);
}
