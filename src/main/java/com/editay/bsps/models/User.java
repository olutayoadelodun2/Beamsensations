package com.editay.bsps.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" }) })

@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max = 20)
	private String username;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	@NotBlank
	@Size(max = 120)
	private String password;
	@NotBlank
	@Size(min = 3, max = 50)
	@Column(name = "firstname")
	private String firstname;
	@NotBlank
	@Size(min = 3, max = 50)
	private String middlename;
	@NotBlank
	@Size(min = 1, max = 50)
	private String surname;
	@NotBlank
	@Size(min = 5, max = 50)
	private String phonenumber;
	@NotBlank
	@Size(min = 5, max = 50)
	private String mobile;
	@NotBlank
	@Size(min = 10, max = 100)
	private String address;
	@NotBlank
	@Size(min = 2, max = 500)
	private String city;
	@NotBlank
	@Size(min = 2, max = 500)
	private String state;
	@NotBlank
	@Size(min = 1, max = 50)
	private String zipcode;
	@NotBlank
	@Size(min = 2, max = 10)
	private String title;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles = new HashSet();

	public User(String first, String middlename, String surname, String phonenumber, String mobile, String address,
			String city, String state, String zipcode, String username, String title, String email, String password) {
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
		this.password = password;
	}
	
	public User(String first, String middlename, String surname, String phonenumber, String mobile, String address,
			String city, String state, String zipcode, String username, String title, String email, Set<Role> roles) {
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
		this.roles = roles;
	}

	public User() {
		// TODO Auto-generated constructor stub
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}