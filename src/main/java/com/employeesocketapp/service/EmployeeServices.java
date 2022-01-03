package com.employeesocketapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeesocketapp.model.Employee;
import com.employeesocketapp.repositories.EmployeeRepositories;

@Service
public class EmployeeServices {

	@Autowired
	EmployeeRepositories employeeRepositories;

	public Employee addEmployee(Employee employee) {
		return employeeRepositories.save(employee);
	}
	
	public List<Employee> listEmployee() {
		return employeeRepositories.findAll();
	}
	
	public void deleteEmployee(Integer employeeId) {
		Employee employee = employeeRepositories.findById(employeeId).get();
		employeeRepositories.delete(employee);
	}
}
