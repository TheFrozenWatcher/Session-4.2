package com.example.demo.controller;

import com.example.demo.model.dto.request.EmployeeRequest;
import com.example.demo.model.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/list")
    public String listEmployees(@RequestParam(defaultValue = "")String name,
                              @RequestParam(defaultValue = "1")Long page,
                              @RequestParam(defaultValue = "2")Long itemPage,
                              @RequestParam(defaultValue = "fullName")String sortBy,
                              @RequestParam(defaultValue = "DESC")String direction,
                              Model model){
        Page<Employee> employeePaging = employeeService.getEmployeePaging(name, page - 1, itemPage, sortBy, direction);
        model.addAttribute("list",employeePaging.getContent());
        model.addAttribute("page",page);
        model.addAttribute("name",name);
        model.addAttribute("sortBy",sortBy);
        model.addAttribute("direction",direction);
        model.addAttribute("listPage",employeePaging.getTotalPages());
        return "/list";
    }



}
