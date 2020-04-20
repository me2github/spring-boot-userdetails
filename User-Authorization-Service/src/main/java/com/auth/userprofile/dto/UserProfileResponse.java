package com.auth.userprofile.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author arunb
 *
 */
public class UserProfileResponse {

	@JsonProperty("UserId")
	private List<UserDetailResponse> userDetails;

	public List<UserDetailResponse> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetailResponse> userDetails) {
		this.userDetails = userDetails;
	}
	
}
