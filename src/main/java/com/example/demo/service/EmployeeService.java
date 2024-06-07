package com.example.demo.service;

import com.example.demo.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(long id);
    Employee save(Employee employee);
    List<Employee> saveAll(List<Employee> employees);
    Employee update(Employee employee);
    void deleteById(long id);
    Page<Employee> findByName(String name, Pageable pageable);
    Page<Employee> getEmployeePaging(String searchName, Long page, Long itemPage, String orderBy, String direction);
}
