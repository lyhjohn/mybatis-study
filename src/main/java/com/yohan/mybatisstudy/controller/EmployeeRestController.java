package com.yohan.mybatisstudy.controller;

import com.yohan.mybatisstudy.mapper.EmployeeMapper;
import com.yohan.mybatisstudy.model.Employee;
import com.yohan.mybatisstudy.model.UserProfile;
import com.yohan.mybatisstudy.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeRestController {

	private final EmployeeMapper mapper;
	private final UserService userService;

	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable("id") Long id) {
		return mapper.getEmployee(id);
	}

	@GetMapping("/all")
	public List<Employee> getUserProfileList() {
		return mapper.getEmployeeList();
	}

	@PostMapping()
	public Employee postEmployee(@RequestBody Employee employee) {
		mapper.insertEmployee(employee);
		return employee;
	}

	@PutMapping("/{id}")
	public int putEmployee(@RequestBody Employee employee, @PathVariable Long id) {
		return mapper.updateEmployee(id, employee);
	}

	@DeleteMapping("/{id}")
	public int deleteEmployee(@PathVariable Long id) {
		return mapper.deleteEmployee(id);
	}

	@PostMapping("/add")
	public UserProfile addEmployee(Employee employee) {
		mapper.insertEmployeeMapper(employee);
		return userService.getUser(String.valueOf(employee.getUser_id()));
	}
}
