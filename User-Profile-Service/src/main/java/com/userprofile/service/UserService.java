package com.userprofile.service;

import java.util.List;

import com.userprofile.dto.UserDetailCreateRequest;
import com.userprofile.dto.UserDetailResponse;
import com.userprofile.dto.UserDetailUpdateRequest;
import com.userprofile.exceptions.UserNotFoundException;

public interface UserService {

	public List<UserDetailResponse> findAllUsers();

	public UserDetailResponse findUserById(Long id) throws UserNotFoundException;

	public UserDetailResponse saveUser(UserDetailCreateRequest user);

	public void deleteById(Long id);

	public UserDetailResponse updateUser(UserDetailUpdateRequest updatedUser, Long id);

	public List<UserDetailResponse> findUserByFilter(UserDetailCreateRequest userDTO);

}
