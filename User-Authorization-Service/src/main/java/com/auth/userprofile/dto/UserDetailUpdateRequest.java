package com.auth.userprofile.dto;

import javax.validation.constraints.DecimalMin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author arunb
 *
 */
public class UserDetailUpdateRequest {

	@JsonProperty("Age")
	@DecimalMin(message = "Age is not valid", value = "1")
	private Integer age;

	@JsonProperty("Address")
	private String address;

	@JsonProperty("MobileNumber")
	private Long mobileNumber;

	@Override
	public String toString() {
		return "UserDetailDTO [age=" + age + ", address=" + address + ", mobileNumber=" + mobileNumber + "]";
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
