package com.userprofile.service;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.userprofile.dto.UserDetailResponse;
import com.userprofile.model.UserDetail;
import com.userprofile.repo.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	UserRepository userRepoeMock;

	@InjectMocks
	UserService userService;
	@Test
	void contextLoads() {
	}
	@Test
	public void testTotalCountFromAllData() {
		List<UserDetail> userList = UserDetailMockObject.getUserDetails();

		when(userService.findAllUsers()).thenReturn(userList.stream().map(UserDetailResponse::of).collect(Collectors.toList()));
		
		List<UserDetailResponse> listUsers = userService.findAllUsers();
		
		Assertions.assertThat(listUsers.size()).isEqualTo(2);
	}
}
