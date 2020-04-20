package com.userprofile.dto;

import java.util.ArrayList;
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

	public static UserProfileResponse of(UserDetailResponse deailResp) {
		UserProfileResponse resp = new UserProfileResponse();
		List<UserDetailResponse> list = new ArrayList<>();
		list.add(deailResp);
		resp.setUserDetails(list);
		return resp;
	}
	
}
