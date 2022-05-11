package com.editay.bsps.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DealerFullDetailsDto {
	private String token;
	private String username;
	private String peakId;

	public String getToken() {
		return this.token;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPeakId() {
		return this.peakId;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public void setPeakId(final String peakId) {
		this.peakId = peakId;
	}

}
