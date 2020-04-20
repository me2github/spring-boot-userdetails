package com.userprofile.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.userprofile.dto.UserDetailResponse;
import com.userprofile.model.UserDetail;
import com.userprofile.service.UserDetailMockObject;
import com.userprofile.service.UserServiceImpl;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserControllerTest {

	@Autowired
	MockMvc mockMVC;

	@MockBean
	public UserServiceImpl service;

	@Test
	public void getUserDetail() {
		List<UserDetail> userList = UserDetailMockObject.getUserDetails();

		when(service.findAllUsers())
				.thenReturn(userList.stream().map(UserDetailResponse::of).collect(Collectors.toList()));
	}

	@Test
	public void createUserDetail() throws Exception {
		List<UserDetail> userList = UserDetailMockObject.getUserDetails();

		UserDetailResponse udResp = new UserDetailResponse();
		udResp = UserDetailResponse.of(userList.get(0));

		when(service.saveUser(any())).thenReturn(udResp);

		this.mockMVC.perform(MockMvcRequestBuilders.post("/RestAPI/users")
				.content(UserDetailMockObject.asJsonString(UserDetailMockObject.getCreateRequest()))
				.contentType(MediaType.APPLICATION_JSON));
		// Assertions.assertThat(udResp.()).isEqualTo(1);
	}

}