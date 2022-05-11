package com.editay.bsps.models;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(
   name = "peakAds2"
)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PeakAd2 {
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

  

}
