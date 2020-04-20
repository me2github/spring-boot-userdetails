package com.userprofile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.userprofile.dto.UserDetailCreateRequest;

@Entity
@Table(name="USER_DETAILS")
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="age")
	private int age;

	@Column(name="address")
	private String address;

	@Column(name="mobile_number")
	private long mobileNumber;

	public static UserDetail of(String firstName, String lastName, int age, String address, long mobileNumber) {
		UserDetail userDetail = new UserDetail();
		userDetail.firstName = firstName;
		userDetail.lastName = lastName;

		userDetail.age = age;
		userDetail.address = address;
		userDetail.mobileNumber = mobileNumber;

		return userDetail;
	}

	public static UserDetail of(UserDetailCreateRequest userDTO) {

		return UserDetail.of(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAge(), userDTO.getAddress(),
				userDTO.getMobileNumber());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", address=" + address + ", mobileNumber=" + mobileNumber + "]";
	}

	public UserDetailCreateRequest toDTO() {
		return UserDetailCreateRequest.of(this.getFirstName(), this.getLastName(), this.getAge(), 
				this.getAddress(), this.getMobileNumber());
	}

}
