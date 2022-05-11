package com.editay.bsps.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PaystackPaymentDto2 {
	   private String amount;
	   private String hoistId;
	   private String email;

	   public String getAmount() {
	      return this.amount;
	   }

	   public String getHoistId() {
	      return this.hoistId;
	   }

	   public String getEmail() {
	      return this.email;
	   }

	   public void setAmount(final String amount) {
	      this.amount = amount;
	   }

	   public void setHoistId(final String hoistId) {
	      this.hoistId = hoistId;
	   }

	   public void setEmail(final String email) {
	      this.email = email;
	   }

	   
	}