package com.example.demo.service.impl;

import com.example.demo.model.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No employee matches."));
    }

    @Override
    public Employee save(Employee employee) {
       return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> saveAll(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    @Override
    public Employee update(Employee employee) {
       return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findByName(String name, Pageable pageable) {
        return employeeRepository.findEmployeeByName(name, pageable);

    }


    @Override
    public Page<Employee> getEmployeePaging(String searchName, Long page, Long itemPage, String orderBy, String direction) {
        Pageable pageable;

        if (orderBy != null && !orderBy.isEmpty()) {
            Sort sort = direction.equalsIgnoreCase("ASC") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
            pageable = PageRequest.of(Math.toIntExact(page), Math.toIntExact(itemPage), sort);
        } else {
            pageable = PageRequest.of(Math.toIntExact(page), Math.toIntExact(itemPage));
        }

        if (searchName != null && !searchName.isEmpty()) {
            return employeeRepository.findEmployeeByName(searchName, pageable);
        } else {
            return employeeRepository.findAll(pageable);
        }
    }

}
