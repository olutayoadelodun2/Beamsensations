package com.editay.bsps.security.jwt;


import com.editay.bsps.security.services.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
   private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
   @Value("${beamsps.app.jwtSecret}")
   private String jwtSecret;
   @Value("${beamsps.app.jwtExpirationMs}")
   private int jwtExpirationMs;

   public String generateJwtToken(Authentication authentication) {
      UserDetailsImpl userPrincipal = (UserDetailsImpl)authentication.getPrincipal();
      return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + (long)this.jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, this.jwtSecret).compact();
   }

   public String getUserNameFromJwtToken(String token) {
      return ((Claims)Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody()).getSubject();
   }

   public boolean validateJwtToken(String authToken) {
      try {
         Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(authToken);
         return true;
      } catch (SignatureException var3) {
         logger.error("Invalid JWT signature: {}", var3.getMessage());
      } catch (MalformedJwtException var4) {
         logger.error("Invalid JWT token: {}", var4.getMessage());
      } catch (ExpiredJwtException var5) {
         logger.error("JWT token is expired: {}", var5.getMessage());
      } catch (UnsupportedJwtException var6) {
         logger.error("JWT token is unsupported: {}", var6.getMessage());
      } catch (IllegalArgumentException var7) {
         logger.error("JWT claims string is empty: {}", var7.getMessage());
      }

      return false;
   }
}
