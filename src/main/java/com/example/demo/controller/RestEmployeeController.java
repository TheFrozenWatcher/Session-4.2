package com.example.demo.controller;

import com.example.demo.model.dto.request.EmployeeRequest;
import com.example.demo.model.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")

public class RestEmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id){
        return new ResponseEntity<>(employeeService.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.save(employee),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.update(employee),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/searchAndPaging")
    public ResponseEntity<Page<Employee>> getStudentWithSearchAndPage(@RequestBody EmployeeRequest employeeRequest) {
        Page<Employee> content = employeeService.getEmployeePaging(
                employeeRequest.getName(),
                employeeRequest.getPage() - 1,
                employeeRequest.getItemPage(),
                employeeRequest.getSortBy(),
                employeeRequest.getDirection()
        );
        return new ResponseEntity<>(content, HttpStatus.OK);
    }
}
