/* Decompiler 6ms, total 197ms, lines 80 */
package com.editay.bsps.payload.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PeakResponse {
   private long id;
   private String peakDescription;
   private long days;
   private long noOfPeak;
   private BigDecimal amount;
   private String peakId;
   Timestamp dateCreated;
   Timestamp lastModified;

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

   public String getPeakId() {
      return this.peakId;
   }

   public Timestamp getDateCreated() {
      return this.dateCreated;
   }

   public Timestamp getLastModified() {
      return this.lastModified;
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

   public void setPeakId(final String peakId) {
      this.peakId = peakId;
   }

   public void setDateCreated(final Timestamp dateCreated) {
      this.dateCreated = dateCreated;
   }

   public void setLastModified(final Timestamp lastModified) {
      this.lastModified = lastModified;
   }
}