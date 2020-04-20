package com.userprofile.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userprofile.dto.UserDetailCreateRequest;
import com.userprofile.dto.UserDetailResponse;
import com.userprofile.dto.UserDetailUpdateRequest;
import com.userprofile.dto.UserProfileResponse;
import com.userprofile.model.UserDetail;
import com.userprofile.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("RestAPI")
@Api(value = "UserDataSet", description = "Perform CRUD operation user data using Rest API")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping(value = "/users")
	@ApiOperation(value = "View the list of Users", response = UserProfileResponse.class)
	public UserProfileResponse getAllUsers() {
		UserProfileResponse resp = null;
		if(service.findAllUsers() != null && service.findAllUsers().size() > 0) {
			resp = new UserProfileResponse();
			resp.setUserDetails(service.findAllUsers());
		}
		return resp;
	}

	@GetMapping(value = "/users/{id}")
	@ApiOperation(value = "Fetch user by its Id", response = UserDetailResponse.class)
	public UserDetailResponse getUserById(@PathVariable @NotBlank Long id) {
		return service.findUserById(id);
	}

	@GetMapping(value = "/users/filter")
	@ApiOperation(value = "Filter user by first Name, last Name, age, and address, mobile-number", 
	response = UserDetail.class)
	public List<UserDetailResponse> getUserByFieldName(
			@RequestParam(value = "FirstName", required = false) String firstName,
			@RequestParam(value = "LastName", required = false) String lastName,
			@RequestParam(value = "Age", required = false) Integer age,
			@RequestParam(value = "Address", required = false) String address,
			@RequestParam(value = "MobileNumber", required = false) Long mobileNumber) {
		return service.findUserByFilter(UserDetailCreateRequest.of(firstName, lastName, age, address, mobileNumber));
	}

	@PostMapping(value = "/users")
	@ApiOperation(value = "Create new User", response = UserProfileResponse.class)
	public ResponseEntity<UserProfileResponse> addUser(@RequestBody @Valid UserDetailCreateRequest user) {
		UserDetailResponse resp = service.saveUser(user);

    	return ResponseEntity.ok()
                .body(UserProfileResponse.of(resp));
	}

	@PatchMapping(value = "/users/{id}")
	@ApiOperation(value = "Update existing User", response = UserDetailResponse.class)
	public ResponseEntity<UserDetailResponse> updateUser(@PathVariable Long id, @RequestBody UserDetailUpdateRequest updatedUser) {
		UserDetailResponse resp = service.updateUser(updatedUser, id);
		return ResponseEntity.accepted().body(resp);
	}

	@DeleteMapping(value = "/users/{id}")
	@ApiOperation(value = "Delete existing User", response = String.class)
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		service.deleteById(id);
    	return ResponseEntity.ok()
                .body("Record is delete for id= " + id);
	}
}
