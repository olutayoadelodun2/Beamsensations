package com.editay.bsps.dto;

import com.editay.bsps.models.Data;

public class PaystackTransactionResponseDto {
   private String status;
   private String message;
   private Data data;

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public Data getData() {
      return this.data;
   }

   public void setData(Data data) {
      this.data = data;
   }
}
