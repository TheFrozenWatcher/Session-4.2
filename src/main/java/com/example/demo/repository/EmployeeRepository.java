package com.example.demo.repository;

import com.example.demo.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, PagingAndSortingRepository<Employee,Long> {

    @Query("select e from Employee e where e.fullname like concat('%',:name,'%') ")
    public Page<Employee> findEmployeeByName(String name, Pageable pageable);
}
