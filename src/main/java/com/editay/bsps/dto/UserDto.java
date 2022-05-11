package com.editay.bsps.dto;


public class UserDto {
	
	
	private Long id;
	private String username;
	private String email;
	private String firstname;
	private String middlename;
	private String surname;
	private String phonenumber;
	private String mobile;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String title;
	private int roleId;
	
	
	public UserDto(String first, String middlename, String surname, String phonenumber, String mobile, String address,
			String city, String state, String zipcode, String username, String title, String email, int roleId) {
		this.firstname = first;
		this.middlename = middlename;
		this.surname = surname;
		this.phonenumber = phonenumber;
		this.mobile = mobile;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.username = username;
		this.title = title;
		this.email = email;
		this.roleId = roleId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getMiddlename() {
		return middlename;
	}


	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	
	
	

}
