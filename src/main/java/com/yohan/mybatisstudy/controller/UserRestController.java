package com.yohan.mybatisstudy.controller;

import com.yohan.mybatisstudy.mapper.UserProfileMapper;
import com.yohan.mybatisstudy.model.UserProfile;
import com.yohan.mybatisstudy.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRestController {

	private final UserProfileMapper mapper;
	private final UserService userService;

	@GetMapping("/user/{id}")
	public UserProfile getUserProfile(@PathVariable("id") Long id) {
		return mapper.getUserProfile(id);
	}

	@GetMapping("/user/all")
	public List<UserProfile> getUserProfileList() {
		return mapper.getUserProfileList();
	}

	@PostMapping("/user")
	public UserProfile postUserProfile(@RequestBody UserProfile userProfile) {
//		mapper.insertUserProfile(userProfile);
		userService.add(userProfile);
		return userProfile;
	}

	@PutMapping("/user/{id}")
	public int putUserProfile(@RequestParam String name, @RequestParam String phone,
		@RequestParam String address, @PathVariable String id) {
		return mapper.updateUserProfile(id, name, phone, address);
	}

	@DeleteMapping("/user/{id}")
	public int deleteUserProfile(@PathVariable String id) {
		return mapper.deleteUserProfile(id);
	}

	@GetMapping("/user/employee")
	public List<UserProfile> getUserProfileAndEmployees() {
		return userService.getAll();
	}

	@GetMapping("/user/mapper/{id}")
	public UserProfile getUser(@PathVariable String id) {
		return userService.getUser(id);
	}

	@GetMapping("/users/mapper")
	public List<UserProfile> getUserList() {
		return userService.getUserList();
	}


	@PostMapping("/user/add")
	public int addUser(UserProfile userProfile) {
		return userService.addUser(userProfile);
	}

	@GetMapping("/user/join/{id}")
	@ResponseBody
	public ResponseEntity<List<UserProfile>> getUserListAndEmployeeUser_Id(@PathVariable String id) {
		return ResponseEntity.ok(mapper.selectUserAndEmployeeUser_ID(Long.parseLong(id)));
	}
}
