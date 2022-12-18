package com.yohan.mybatisstudy.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResParam {

	private Long id;
	private String username;
	private String userPhone;
	private String address;
	private List<Employee> employees = new ArrayList<>();
	private String employee_name;
}
