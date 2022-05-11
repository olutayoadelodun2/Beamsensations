package com.editay.bsps.payload.request;

import com.editay.bsps.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {
   @NotBlank
   @Size(
      min = 3,
      max = 50
   )
   private String firstname;
   @NotBlank
   @Size(
      min = 1,
      max = 50
   )
   private String middlename;
   @NotBlank
   @Size(
      min = 3,
      max = 50
   )
   private String surname;
   @NotBlank
   @Size(
      min = 5,
      max = 50
   )
   private String phonenumber;
   @NotBlank
   @Size(
      min = 3,
      max = 50
   )
   private String username;
   @NotBlank
   @Size(
      max = 60
   )
   @Email
   private String email;
   @NotBlank
   @Size(
      min = 5,
      max = 50
   )
   private String mobile;
   @NotBlank
   @Size(
      min = 10,
      max = 100
   )
   private String address;
   @NotBlank
   @Size(
      min = 2,
      max = 500
   )
   private String city;
   @NotBlank
   @Size(
      min = 2,
      max = 500
   )
   private String state;
   @NotBlank
   @Size(
      min = 1,
      max = 50
   )
   private String zipcode;
   @NotBlank
   @Size(
      min = 2,
      max = 10
   )
   private String title;
   private Set<String> role;
   @NotBlank
   @Size(
      min = 6,
      max = 40
   )
   private String password;

   public SignupRequest() {
   }

   public SignupRequest(User user) {
      this.address = user.getAddress();
      this.city = user.getCity();
      this.email = user.getEmail();
      this.firstname = user.getFirstname();
      this.middlename = user.getMiddlename();
      this.mobile = user.getMobile();
      this.phonenumber = user.getPhonenumber();
      this.state = user.getState();
      this.surname = user.getSurname();
      this.title = user.getTitle();
      this.username = user.getUsername();
      this.zipcode = user.getZipcode();
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Set<String> getRole() {
      return this.role;
   }

   public void setRole(Set<String> role) {
      this.role = role;
   }

   public String getFirstname() {
      return this.firstname;
   }

   public void setFirstname(String first) {
      this.firstname = first;
   }

   public String getMiddlename() {
      return this.middlename;
   }

   public void setMiddlename(String middlename) {
      this.middlename = middlename;
   }

   public String getSurname() {
      return this.surname;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   public String getPhonenumber() {
      return this.phonenumber;
   }

   public void setPhonenumber(String phonenumber) {
      this.phonenumber = phonenumber;
   }

   public String getMobile() {
      return this.mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getCity() {
      return this.city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getZipcode() {
      return this.zipcode;
   }

   public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }
}
