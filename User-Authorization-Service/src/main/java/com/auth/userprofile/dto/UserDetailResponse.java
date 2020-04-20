package com.auth.userprofile.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author arunb
 *
 */
public class UserDetailResponse {

	@JsonProperty("UserId")
	private Long userId;

	@JsonProperty("FirstName")
	private String firstName;

	@JsonProperty("LastName")
	private String lastName;

	@JsonProperty("Age")
	private Integer age;

	@JsonProperty("Address")
	private String address;

	@JsonProperty("MobileNumber")
	private Long mobileNumber;


	@Override
	public String toString() {
		return "UserDetailResponse [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", age="
				+ age + ", address=" + address + ", mobileNumber=" + mobileNumber + "]";
	}
}
