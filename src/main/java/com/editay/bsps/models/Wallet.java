package com.editay.bsps.models;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(
   name = "wallet"
)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Wallet {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   Long id;
   @Column
   @JsonIgnore
   String username;
   @Column(
      name = "walletAmount",
      nullable = false
   )
   private BigDecimal walletAmount;
   @Column
   String dateCreated;

   public Wallet(String username, BigDecimal walletAmount) {
      this.username = username;
      this.walletAmount = walletAmount;
   }

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public BigDecimal getWalletAmount() {
	return walletAmount;
}

public void setWalletAmount(BigDecimal walletAmount) {
	this.walletAmount = walletAmount;
}

public String getDateCreated() {
	return dateCreated;
}

public void setDateCreated(String dateCreated) {
	this.dateCreated = dateCreated;
}
   
   

  
   
}
