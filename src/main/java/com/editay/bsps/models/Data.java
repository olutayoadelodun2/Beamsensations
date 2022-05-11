package com.editay.bsps.models;

public class Data {
	   private String authorization_url;
	   private String access_code;
	   private String reference;

	   public String getAuthorization_url() {
	      return this.authorization_url;
	   }

	   public void setAuthorization_url(String authorization_url) {
	      this.authorization_url = authorization_url;
	   }

	   public String getAccess_code() {
	      return this.access_code;
	   }

	   public void setAccess_code(String access_code) {
	      this.access_code = access_code;
	   }

	   public String getReference() {
	      return this.reference;
	   }

	   public void setReference(String reference) {
	      this.reference = reference;
	   }
	}