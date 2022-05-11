/* Decompiler 7ms, total 192ms, lines 48 */
package com.editay.bsps.dto;

import java.math.BigDecimal;

public class WalletDto {
   private String username;
   private BigDecimal walletAmount;
   private long id;

   public WalletDto(String username, BigDecimal walletAmount) {
      this.username = username;
      this.walletAmount = walletAmount;
   }

   public WalletDto(String username, BigDecimal walletAmount, long id) {
      this.username = username;
      this.walletAmount = walletAmount;
      this.id = id;
   }

   public String getUsername() {
      return this.username;
   }

   public BigDecimal getWalletAmount() {
      return this.walletAmount;
   }

   public long getId() {
      return this.id;
   }

   public void setUsername(final String username) {
      this.username = username;
   }

   public void setWalletAmount(final BigDecimal walletAmount) {
      this.walletAmount = walletAmount;
   }

   public void setId(final long id) {
      this.id = id;
   }

   public WalletDto() {
   }
}