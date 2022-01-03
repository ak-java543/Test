package com.employeesocketapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeesocketapp.model.Employee;
import com.employeesocketapp.model.Request;
import com.employeesocketapp.service.EmployeeServices;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeServices employeeServices;

	@MessageMapping("/add-employee")
	@SendTo("/topic/list-employee")
	public List<Employee> addEmployee(@RequestBody Employee employee) {
		employee.setEmployeeId(null);
		employeeServices.addEmployee(employee);
		return listEmployees();
	}

	@MessageMapping("/del-employee")
	@SendTo("/topic/list-employee")
	public List<Employee> delEmployee(@PathVariable Request request) {
		employeeServices.deleteEmployee((Integer) request.getInputData());
		return listEmployees();
	}

	public List<Employee> listEmployees() {
		return employeeServices.listEmployee();
	}
}
