/* Decompiler 5ms, total 804ms, lines 65 */
package com.editay.bsps.payload.response;

import java.util.List;

public class JwtResponse {
   private String token;
   private String type = "Bearer";
   private Long id;
   private String username;
   private String email;
   private List<String> roles;

   public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
      this.token = accessToken;
      this.id = id;
      this.username = username;
      this.email = email;
      this.roles = roles;
   }

   public String getAccessToken() {
      return this.token;
   }

   public void setAccessToken(String accessToken) {
      this.token = accessToken;
   }

   public String getTokenType() {
      return this.type;
   }

   public void setTokenType(String tokenType) {
      this.type = tokenType;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public List<String> getRoles() {
      return this.roles;
   }
}