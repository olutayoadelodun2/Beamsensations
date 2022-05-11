package com.editay.bsps.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PaystackPaymentDto {
	private String amount;
	private String peakId;
	private String email;
	private String username;

	public String getAmount() {
		return this.amount;
	}

	public String getPeakId() {
		return this.peakId;
	}

	public String getEmail() {
		return this.email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setAmount(final String amount) {
		this.amount = amount;
	}

	public void setPeakId(final String peakId) {
		this.peakId = peakId;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

}
