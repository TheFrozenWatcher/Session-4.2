package com.example.demo.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRequest {
    private String name;
    private Long page;
    private Long itemPage;
    private String sortBy;
    private String direction;
}