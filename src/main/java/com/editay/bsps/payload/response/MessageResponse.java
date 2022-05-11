/* Decompiler 1ms, total 773ms, lines 18 */
package com.editay.bsps.payload.response;

public class MessageResponse {
   private String message;

   public MessageResponse(String message) {
      this.message = message;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }
}