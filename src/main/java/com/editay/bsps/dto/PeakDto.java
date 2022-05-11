package com.editay.bsps.dto;

import java.math.BigDecimal;

public class PeakDto {
	   private String peakDescription;
	   private long days;
	   private long noOfPeak;
	   private BigDecimal amount;
	   private String peakId;

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

	   public String getPeakId() {
	      return this.peakId;
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

	   public void setPeakId(final String peakId) {
	      this.peakId = peakId;
	   }
	}
