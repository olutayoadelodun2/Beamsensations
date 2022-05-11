package com.editay.bsps.models;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
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
public class KYC {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   Long id;
   @Column
   String nin;
   @Column
   String accountNumber;
   @Column
   String bankName;
   @Column
   String passport;
   @Column
   String cacDocument;
   @Column
   String brand;
   @Column
   @JsonIgnore
   String username;
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
public String getNin() {
	return nin;
}
public void setNin(String nin) {
	this.nin = nin;
}
public String getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}
public String getBankName() {
	return bankName;
}
public void setBankName(String bankName) {
	this.bankName = bankName;
}
public String getPassport() {
	return passport;
}
public void setPassport(String passport) {
	this.passport = passport;
}
public String getCacDocument() {
	return cacDocument;
}
public void setCacDocument(String cacDocument) {
	this.cacDocument = cacDocument;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
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
