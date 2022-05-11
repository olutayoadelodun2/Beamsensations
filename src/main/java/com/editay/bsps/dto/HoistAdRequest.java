package com.editay.bsps.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
@EqualsAndHashCode
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
   
   public HoistAdRequest() {
	      
	   }
   
   
   public long getId() {
	      return this.id;
   }
   
   public void setId(long id) {
	   
	   this.id = id;
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
