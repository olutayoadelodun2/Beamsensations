package com.editay.bsps.dto;

import com.editay.bsps.utility.Channels;
import com.editay.bsps.utility.PaystackBearer;

public class PaystackTransactionRequestDto {
   private String amount;
   private String email;
   private String reference;
   private String callback_url;
   private Integer invoice_limit;
   private Channels[] channels;
   private String subaccount;
   private Integer transaction_charge;
   private PaystackBearer paystackBearer;

   public PaystackTransactionRequestDto() {
      this.paystackBearer = PaystackBearer.ACCOUNT;
   }

   public PaystackTransactionRequestDto(String amount, String email) {
      this.paystackBearer = PaystackBearer.ACCOUNT;
      this.amount = amount;
      this.email = email;
   }

   public String getAmount() {
      return this.amount;
   }

   public void setAmount(String amount) {
      this.amount = amount;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getReference() {
      return this.reference;
   }

   public void setReference(String reference) {
      this.reference = reference;
   }

   public String getCallback_url() {
      return this.callback_url;
   }

   public void setCallback_url(String callback_url) {
      this.callback_url = callback_url;
   }

   public Integer getInvoice_limit() {
      return this.invoice_limit;
   }

   public void setInvoice_limit(Integer invoice_limit) {
      this.invoice_limit = invoice_limit;
   }

   public Channels[] getChannels() {
      return this.channels;
   }

   public void setChannels(Channels[] channels) {
      this.channels = channels;
   }

   public String getSubaccount() {
      return this.subaccount;
   }

   public void setSubaccount(String subaccount) {
      this.subaccount = subaccount;
   }

   public Integer getTransaction_charge() {
      return this.transaction_charge;
   }

   public void setTransaction_charge(Integer transaction_charge) {
      this.transaction_charge = transaction_charge;
   }

   public PaystackBearer getPaystackBearer() {
      return this.paystackBearer;
   }

   public void setPaystackBearer(PaystackBearer paystackBearer) {
      this.paystackBearer = paystackBearer;
   }
}
