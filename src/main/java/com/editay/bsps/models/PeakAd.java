package com.editay.bsps.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
   name = "peakAds"
)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PeakAd {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   Long id;
   @Column(
      name = "peak_description",
      nullable = false,
      columnDefinition = "TEXT"
   )
   private String peakDescription;
   @Column(
      name = "days",
      nullable = false
   )
   private long days;
   @Column(
      name = "noOfPeaks",
      nullable = false
   )
   private long noOfPeak;
   @Column(
      name = "amount",
      nullable = false
   )
   private BigDecimal amount;
   @Column
   @JsonIgnore
   String username;
   @Column(
      columnDefinition = "varchar(255) default 'UNAPPROVED'"
   )
   String accountStatus;
   @Column(
      columnDefinition = "varchar(255) default '₦ 0'"
   )
   String balance;
   @Column(
      name = "peak_id"
   )
   private String peakId;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @Column(
      name = "subscription_date"
   )
   private LocalDateTime subscriptionDate;
   @Column(
      name = "subscription_expire"
   )
   private String subscriptionExpireDate;
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
public String getPeakDescription() {
	return peakDescription;
}
public void setPeakDescription(String peakDescription) {
	this.peakDescription = peakDescription;
}
public long getDays() {
	return days;
}
public void setDays(long days) {
	this.days = days;
}
public long getNoOfPeak() {
	return noOfPeak;
}
public void setNoOfPeak(long noOfPeak) {
	this.noOfPeak = noOfPeak;
}
public BigDecimal getAmount() {
	return amount;
}
public void setAmount(BigDecimal amount) {
	this.amount = amount;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getAccountStatus() {
	return accountStatus;
}
public void setAccountStatus(String accountStatus) {
	this.accountStatus = accountStatus;
}
public String getBalance() {
	return balance;
}
public void setBalance(String balance) {
	this.balance = balance;
}
public String getPeakId() {
	return peakId;
}
public void setPeakId(String peakId) {
	this.peakId = peakId;
}
public LocalDateTime getSubscriptionDate() {
	return subscriptionDate;
}
public void setSubscriptionDate(LocalDateTime subscriptionDate) {
	this.subscriptionDate = subscriptionDate;
}
public String getSubscriptionExpireDate() {
	return subscriptionExpireDate;
}
public void setSubscriptionExpireDate(String subscriptionExpireDate) {
	this.subscriptionExpireDate = subscriptionExpireDate;
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
