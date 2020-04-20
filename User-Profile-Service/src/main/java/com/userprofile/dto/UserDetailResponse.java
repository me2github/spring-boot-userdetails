package com.userprofile.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.userprofile.model.UserDetail;

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

	public static UserDetailResponse of(UserDetail userDetail) {
		UserDetailResponse user = new UserDetailResponse();
		user.userId = userDetail.getId();
		user.firstName = userDetail.getFirstName();
		user.lastName = userDetail.getLastName();

		user.age = userDetail.getAge();
		user.address = userDetail.getAddress();
		user.mobileNumber = userDetail.getMobileNumber();

		return user;
	}

	@Override
	public String toString() {
		return "UserDetailResponse [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", age="
				+ age + ", address=" + address + ", mobileNumber=" + mobileNumber + "]";
	}
}
