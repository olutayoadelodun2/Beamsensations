package com.editay.bsps.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class PeakAdRequest {
   private long id;
   private String peakDescription;
   private long days;
   private long noOfPeak;
   private BigDecimal amount;
   private String username;
   private String accountStatus;
   private String balance;
   private String peakId;
   private String subscriptionDate;
   
   

   public PeakAdRequest(long id, String balance, String peakId) {
      this.id = id;
      this.balance = balance;
      this.peakId = peakId;
   }
   
   public PeakAdRequest() {
	      
	   }
   
   public PeakAdRequest(long id, BigDecimal amount,String balance, String peakId) {
	      this.id = id;
	      this.amount = amount;
	      this.balance = balance;
	      this.peakId = peakId;
	   }

   public PeakAdRequest(long id, long days, long noOfPeak, BigDecimal amount, String username, String accountStatus, String balance, String peakId, String subscriptionDate) {
      this.id = id;
      this.days = days;
      this.noOfPeak = noOfPeak;
      this.amount = amount;
      this.username = username;
      this.accountStatus = accountStatus;
      this.balance = balance;
      this.peakId = peakId;
      this.subscriptionDate = subscriptionDate;
   }

   public long getId() {
      return this.id;
   }

   public String getPeakDescription() {
      return this.peakDescription;
   }

   public long getDays() {
      return this.days;
   }

   public long getNoOfPeak() {
      return this.noOfPeak;
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

   public String getPeakId() {
      return this.peakId;
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

   public void setDays(final long days) {
      this.days = days;
   }

   public void setNoOfPeak(final long noOfPeak) {
      this.noOfPeak = noOfPeak;
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

   public void setPeakId(final String peakId) {
      this.peakId = peakId;
   }

   public void setSubscriptionDate(final String subscriptionDate) {
      this.subscriptionDate = subscriptionDate;
   }

  
}
