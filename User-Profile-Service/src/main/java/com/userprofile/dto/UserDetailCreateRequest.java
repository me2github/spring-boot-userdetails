package com.userprofile.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author arunb
 *
 */
public class UserDetailCreateRequest {

	@NotNull(message = "First name is mandatory")
	@NotBlank(message = "First name is mandatory")
    @JsonProperty("FirstName")
	private String firstName;

	@NotNull(message = "Last name is mandatory")
	@NotBlank(message = "Last name is mandatory")
    @JsonProperty("LastName")
	private String lastName;

    @JsonProperty("Age")
	@DecimalMin(message = "Age is not valid", value = "1")
	private Integer age;

    @JsonProperty("Address")
	private String address;

    @JsonProperty("MobileNumber")
	private Long mobileNumber;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@Override
	public String toString() {
		return "UserDetailDTO [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", address="
				+ address + ", mobileNumber=" + mobileNumber + "]";
	}

	public static UserDetailCreateRequest of(String firstName, String lastName, Integer age, 
			String address, Long mobileNumber) {
		UserDetailCreateRequest user = new UserDetailCreateRequest();
		user.firstName = firstName;
		user.lastName = lastName;

		user.age = age;
		user.address = address;
		user.mobileNumber = mobileNumber;

		return user;
	}
}
