package com.editay.bsps.dto;

public class FreeAdRequest {
	   private long id;
	   private String freeDescription;
	   private long days;
	   private String username;
	   private String accountStatus;
	   private String freeId;
	   private String subscriptionDate;

	   public FreeAdRequest(long id, String freeId) {
	      this.id = id;
	      this.freeId = freeId;
	   }

	   public long getId() {
	      return this.id;
	   }

	   public String getFreeDescription() {
	      return this.freeDescription;
	   }

	   public long getDays() {
	      return this.days;
	   }

	   public String getUsername() {
	      return this.username;
	   }

	   public String getAccountStatus() {
	      return this.accountStatus;
	   }

	   public String getFreeId() {
	      return this.freeId;
	   }

	   public String getSubscriptionDate() {
	      return this.subscriptionDate;
	   }

	   public void setId(final long id) {
	      this.id = id;
	   }

	   public void setFreeDescription(final String freeDescription) {
	      this.freeDescription = freeDescription;
	   }

	   public void setDays(final long days) {
	      this.days = days;
	   }

	   public void setUsername(final String username) {
	      this.username = username;
	   }

	   public void setAccountStatus(final String accountStatus) {
	      this.accountStatus = accountStatus;
	   }

	   public void setFreeId(final String freeId) {
	      this.freeId = freeId;
	   }

	   public void setSubscriptionDate(final String subscriptionDate) {
	      this.subscriptionDate = subscriptionDate;
	   }

	   public FreeAdRequest() {
	   }
	}
