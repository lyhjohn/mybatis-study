package com.yohan.mybatisstudy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

	private Long id;
	private int user_id;
	private String name;
	private String address;
}
