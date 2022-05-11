package com.editay.bsps.security;

import com.editay.bsps.security.jwt.AuthEntryPointJwt;
import com.editay.bsps.security.jwt.AuthTokenFilter;
import com.editay.bsps.security.services.UserDetailsServiceImpl;
import com.editay.bsps.service.CarService;
import com.editay.bsps.service.DefaultCarService;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
   prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
   @Autowired
   UserDetailsServiceImpl userDetailsService;
   @Autowired
   private AuthEntryPointJwt unauthorizedHandler;

   @Bean
   public AuthTokenFilter authenticationJwtTokenFilter() {
      return new AuthTokenFilter();
   }

   @Bean
   public CarService carService() {
      return new DefaultCarService();
   }

   public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
      authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
   }

   public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers(new String[]{"/v2/api-docs", "/configuration/**", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/api-docs/**"});
   }

   @Bean
   public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
	   logger.info("WebSecurityConfig : Reached");
	   http.cors().and().csrf().disable().
      // http.
               authorizeRequests()
               .antMatchers("/api/auth/**").permitAll().antMatchers("/api/payment/**").permitAll().antMatchers("/api/peakAds/**").permitAll().antMatchers("/api/peakAds2/**").permitAll()
               .antMatchers("/api/client/**").permitAll().antMatchers("/api/peaksubscriptions/**").permitAll().antMatchers("/api/wallet/**").permitAll()
               .antMatchers("/api/freeAdsCarUpload/**").permitAll().antMatchers("/api/freeAds/**").permitAll().antMatchers("/api/peakAdsCarUpload/**").permitAll().antMatchers("/api/allcarsdetails/**").permitAll()
               .anyRequest().authenticated()
               .and()
               .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       
       http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
   }

	/*
	 * protected void configure(HttpSecurity http) throws Exception {
	 * logger.info("WebSecurityConfig : Reached");
	 * ((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((
	 * AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl
	 * )((HttpSecurity)((HttpSecurity)((HttpSecurity)((HttpSecurity)http.cors().and(
	 * )).csrf().disable()).exceptionHandling().authenticationEntryPoint(this.
	 * unauthorizedHandler).and()).sessionManagement().sessionCreationPolicy(
	 * SessionCreationPolicy.STATELESS).and()).authorizeRequests().antMatchers(new
	 * String[]{"/api/auth/**"})).permitAll().antMatchers(new
	 * String[]{"/api/test/**"})).permitAll() .antMatchers(new
	 * String[]{"/api/peakAds/**"})).permitAll().antMatchers(new
	 * String[]{"/api/peakAds2/**"})).permitAll().antMatchers(new
	 * String[]{"/api/payment/**"})).permitAll().antMatchers(new
	 * String[]{"/api/client/**"})).permitAll().antMatchers(new
	 * String[]{"/api/peaksubscriptions/**"})).permitAll().antMatchers(new
	 * String[]{"/api/wallet/**"})).permitAll().anyRequest()).authenticated();
	 * http.addFilterBefore(this.authenticationJwtTokenFilter(),
	 * UsernamePasswordAuthenticationFilter.class); }
	 */

   @Bean
   public String randomPassword() {
      String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
      StringBuilder salt = new StringBuilder();
      Random random = new Random();

      while(salt.length() < 18) {
         int i = (int)(random.nextFloat() * (float)SALTCHARS.length());
         salt.append(SALTCHARS.charAt(i));
      }

      String saltStr = salt.toString();
      return saltStr;
   }

  
}
