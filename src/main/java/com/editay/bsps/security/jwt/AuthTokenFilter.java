package com.editay.bsps.security.jwt;

import com.editay.bsps.security.services.UserDetailsServiceImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter {
   @Autowired
   private JwtUtils jwtUtils;
   @Autowired
   private UserDetailsServiceImpl userDetailsService;
   private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      try {
         String jwt = this.parseJwt(request);
         if (jwt != null && this.jwtUtils.validateJwtToken(jwt)) {
            String username = this.jwtUtils.getUserNameFromJwtToken(jwt);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, (Object)null, userDetails.getAuthorities());
            authentication.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
         }
      } catch (Exception var8) {
         logger.error("Cannot set user authentication: {}", var8);
      }

      filterChain.doFilter(request, response);
   }

   private String parseJwt(HttpServletRequest request) {
      String headerAuth = request.getHeader("Authorization");
      return StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ") ? headerAuth.substring(7, headerAuth.length()) : null;
   }
}
