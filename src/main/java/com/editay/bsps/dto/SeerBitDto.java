package com.editay.bsps.dto;

public class SeerBitDto {
	   private String key;
	   private String publicKey;
	   private String amount;
	   private String paymentReference;
	   private String email;
	   private String productId;
	   private String productDescription;
	   private String callBackUrl;
	   private String currency;
	   private String country;
	   private String hash;
	   private String hashType;
	   private String encryptedKey;

	   public SeerBitDto(String publicKey, String amount, String currency, String country, String paymentReference, String email, String productId, String productDescription, String callBackUrl) {
	      this.amount = amount;
	      this.publicKey = publicKey;
	      this.currency = currency;
	      this.country = country;
	      this.paymentReference = paymentReference;
	      this.email = email;
	      this.productId = productId;
	      this.productDescription = productDescription;
	      this.callBackUrl = callBackUrl;
	   }

	   public SeerBitDto(String publicKey, String amount, String currency, String country, String paymentReference, String email, String productId, String productDescription, String callBackUrl, String hash, String hashType) {
	      this.amount = amount;
	      this.publicKey = publicKey;
	      this.currency = currency;
	      this.country = country;
	      this.paymentReference = paymentReference;
	      this.email = email;
	      this.productId = productId;
	      this.productDescription = productDescription;
	      this.callBackUrl = callBackUrl;
	      this.hash = hash;
	      this.hashType = hashType;
	   }

	   public String getKey() {
	      return this.key;
	   }

	   public String getPublicKey() {
	      return this.publicKey;
	   }

	   public String getAmount() {
	      return this.amount;
	   }

	   public String getPaymentReference() {
	      return this.paymentReference;
	   }

	   public String getEmail() {
	      return this.email;
	   }

	   public String getProductId() {
	      return this.productId;
	   }

	   public String getProductDescription() {
	      return this.productDescription;
	   }

	   public String getCallBackUrl() {
	      return this.callBackUrl;
	   }

	   public String getCurrency() {
	      return this.currency;
	   }

	   public String getCountry() {
	      return this.country;
	   }

	   public String getHash() {
	      return this.hash;
	   }

	   public String getHashType() {
	      return this.hashType;
	   }

	   public String getEncryptedKey() {
	      return this.encryptedKey;
	   }

	   public void setKey(final String key) {
	      this.key = key;
	   }

	   public void setPublicKey(final String publicKey) {
	      this.publicKey = publicKey;
	   }

	   public void setAmount(final String amount) {
	      this.amount = amount;
	   }

	   public void setPaymentReference(final String paymentReference) {
	      this.paymentReference = paymentReference;
	   }

	   public void setEmail(final String email) {
	      this.email = email;
	   }

	   public void setProductId(final String productId) {
	      this.productId = productId;
	   }

	   public void setProductDescription(final String productDescription) {
	      this.productDescription = productDescription;
	   }

	   public void setCallBackUrl(final String callBackUrl) {
	      this.callBackUrl = callBackUrl;
	   }

	   public void setCurrency(final String currency) {
	      this.currency = currency;
	   }

	   public void setCountry(final String country) {
	      this.country = country;
	   }

	   public void setHash(final String hash) {
	      this.hash = hash;
	   }

	   public void setHashType(final String hashType) {
	      this.hashType = hashType;
	   }

	   public void setEncryptedKey(final String encryptedKey) {
	      this.encryptedKey = encryptedKey;
	   }

	   public SeerBitDto() {
	   }
	}
