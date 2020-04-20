package com.userprofile.service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.userprofile.dto.UserDetailCreateRequest;
import com.userprofile.model.UserDetail;

public class UserDetailMockObject {

	public static List<UserDetail> getUserDetails() {
		List<UserDetail> userList = new ArrayList<>();
		userList.add(UserDetail.of("Denial", "Helmis", 42, "Noida", 9538749210L));
		userList.add(UserDetail.of("arun", "Bhardwaj", 32, "Noida", 9538749210L));
		return userList;
	}
	
	public static UserDetailCreateRequest getCreateRequest() {
		UserDetailCreateRequest request = new UserDetailCreateRequest();
		request.setAddress("Noida");
		request.setAge(42);
		request.setFirstName("Denial");
		request.setLastName("Helmis");
		request.setMobileNumber(9538749210L);
		return request;
	}
	
	public static String asJsonString(final Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new Jdk8Module());

			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
