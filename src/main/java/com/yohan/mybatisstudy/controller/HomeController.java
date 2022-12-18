package com.yohan.mybatisstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "user";
	}

	@GetMapping("/employeeHome")
	public String employeeHome() {
		return "employee";
	}
}
