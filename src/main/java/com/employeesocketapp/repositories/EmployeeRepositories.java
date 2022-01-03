package com.employeesocketapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeesocketapp.model.Employee;

@Repository
public interface EmployeeRepositories extends JpaRepository<Employee, Integer>{
	
}
