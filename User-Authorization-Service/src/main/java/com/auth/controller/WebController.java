package com.auth.controller;

import java.util.List;

import javax.jms.Queue;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.auth.model.User;
import com.auth.model.UserDetailUpdateJMSRequest;
import com.auth.repo.UserAuthRepository;
import com.auth.userprofile.dto.UserDetailCreateRequest;
import com.auth.userprofile.dto.UserProfileResponse;

import io.swagger.annotations.ApiOperation;

/**
 * @author arunb
 *
 */
@RestController
@RequestMapping("api")
public class WebController {
	private final Logger logger = LoggerFactory.getLogger(WebController.class);
	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private Queue queue;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	UserAuthRepository repo;

	@PutMapping(value = "/users/{id}")
	@ApiOperation(value = "Update existing User", response = String.class)
	public ResponseEntity<String> updateUserInAsync(@PathVariable Long id,
			@RequestBody UserDetailUpdateJMSRequest updateRequest) {
		logger.info("Request send to message queue for update user detail");
		jmsTemplate.convertAndSend(queue, updateRequest);
		return new ResponseEntity<String>("Record is successfully updated for id = " + id, HttpStatus.OK);
	}

	@DeleteMapping(value = "/users/{id}")
	@ApiOperation(value = "Delete existing User", response = String.class)
	public ResponseEntity<String> deleteUserInAsync(@PathVariable Long id) {
		logger.info("Request send to message queue for delete user detail");
		jmsTemplate.convertAndSend(queue, id);
		return new ResponseEntity<String>("Record is successfully deleted for id = " + id, HttpStatus.OK);
	}

	@GetMapping(value = "/users")
	@ApiOperation(value = "View the list of Users", response = ResponseEntity.class)
	public ResponseEntity<UserProfileResponse> getAllUsers() {
		logger.info("Request to get all user details");
		UserProfileResponse resp = restTemplate.getForObject("http://localhost:8080/RestAPI/users",
				UserProfileResponse.class);
		return ResponseEntity.accepted().body(resp);
	}

	/**
	 * @param user
	 * @return
	 */
	@PostMapping(value = "/users")
	@ApiOperation(value = "Create new User", response = UserProfileResponse.class)
	public ResponseEntity<UserProfileResponse> addUser(@RequestBody @Valid UserDetailCreateRequest createRequest) {
		logger.info("Request send to create new detail");
		ResponseEntity<UserProfileResponse> resp = restTemplate.postForEntity("http://localhost:8080/RestAPI/users",
				createRequest, UserProfileResponse.class);
		return resp;
	}

	@GetMapping(value = "/account")
	@ApiOperation(value = "View the list of Users", response = ResponseEntity.class)
	public List<User> getAllUserAccount() {
		return repo.findAll();
	}
}
