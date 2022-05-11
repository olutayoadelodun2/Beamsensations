package com.editay.bsps.models;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PeakSubscription {
   @Id
   @GeneratedValue
   @Column(
      updatable = false,
      nullable = false
   )
   Long id;
   @Column
   String peakid;
   @Column
   @JsonIgnore
   String username;
   @Column
   String email;
   @Column
   String amount;
   @Column
   String authorizationUrl;
   @Column
   String reference;
   @Column
   String accessCode;
   @Column
   String firstname;
   @Column
   String middlename;
   @Column
   String surname;
   @Column
   String phonenumber;
   @Column
   String mobile;
   @Column
   String address;
   @Column
   String city;
   @Column
   String state;
   @Column
   String zipcode;
   @Column
   String title;
   @Column
   String status;
   @CreationTimestamp
   @Column(
      updatable = false
   )
   Timestamp dateCreated;
   @UpdateTimestamp
   Timestamp lastModified;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getPeakid() {
	return peakid;
}
public void setPeakid(String peakid) {
	this.peakid = peakid;
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
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public String getAuthorizationUrl() {
	return authorizationUrl;
}
public void setAuthorizationUrl(String authorizationUrl) {
	this.authorizationUrl = authorizationUrl;
}
public String getReference() {
	return reference;
}
public void setReference(String reference) {
	this.reference = reference;
}
public String getAccessCode() {
	return accessCode;
}
public void setAccessCode(String accessCode) {
	this.accessCode = accessCode;
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
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Timestamp getDateCreated() {
	return dateCreated;
}
public void setDateCreated(Timestamp dateCreated) {
	this.dateCreated = dateCreated;
}
public Timestamp getLastModified() {
	return lastModified;
}
public void setLastModified(Timestamp lastModified) {
	this.lastModified = lastModified;
}
   
   
   

   
}
