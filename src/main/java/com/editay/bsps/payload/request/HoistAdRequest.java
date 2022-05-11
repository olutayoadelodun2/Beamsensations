package com.editay.bsps.payload.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class HoistAdRequest {

	   private long id;
	   private String peakDescription;
	   private long noOfMonths;
	   private BigDecimal amount;
	   private String username;
	   private String accountStatus;
	   private String balance;
	   private String hoistId;
	   private String subscriptionDate;

	   public HoistAdRequest(long id, String balance, String hoistId) {
	      this.id = id;
	      this.balance = balance;
	      this.hoistId = hoistId;
	   }

	   public long getId() {
	      return this.id;
	   }

	   public String getPeakDescription() {
	      return this.peakDescription;
	   }

	   public long getNoOfMonths() {
	      return this.noOfMonths;
	   }

	   public BigDecimal getAmount() {
	      return this.amount;
	   }

	   public String getUsername() {
	      return this.username;
	   }

	   public String getAccountStatus() {
	      return this.accountStatus;
	   }

	   public String getBalance() {
	      return this.balance;
	   }

	   public String getHoistId() {
	      return this.hoistId;
	   }

	   public String getSubscriptionDate() {
	      return this.subscriptionDate;
	   }

	   public void setId(final long id) {
	      this.id = id;
	   }

	   public void setPeakDescription(final String peakDescription) {
	      this.peakDescription = peakDescription;
	   }

	   public void setNoOfMonths(final long noOfMonths) {
	      this.noOfMonths = noOfMonths;
	   }

	   public void setAmount(final BigDecimal amount) {
	      this.amount = amount;
	   }

	   public void setUsername(final String username) {
	      this.username = username;
	   }

	   public void setAccountStatus(final String accountStatus) {
	      this.accountStatus = accountStatus;
	   }

	   public void setBalance(final String balance) {
	      this.balance = balance;
	   }

	   public void setHoistId(final String hoistId) {
	      this.hoistId = hoistId;
	   }

	   public void setSubscriptionDate(final String subscriptionDate) {
	      this.subscriptionDate = subscriptionDate;
	   }

	  

	  
	  
	}
