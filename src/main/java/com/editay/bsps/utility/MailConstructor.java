package com.editay.bsps.utility;

import com.editay.bsps.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

@Component
public class MailConstructor {
   @Autowired
   private Environment env;
   private TemplateEngine templateEngine;

   public SimpleMailMessage constructNewUserEmail(User user, String password) {
      String message = "\nPlease use the following credentials to log in and edit your personal information\nUsername: " + user.getUsername() + "\nPassword: " + password;
      SimpleMailMessage email = new SimpleMailMessage();
      email.setSubject("Your new Password");
      email.setTo(user.getEmail());
      email.setText(message);
      email.setFrom(this.env.getProperty("support.email"));
      return email;
   }

   public SimpleMailMessage constructCongratulatoryEmail(User user) {
      String message = "\nThank you " + user.getMiddlename() + " " + user.getSurname() + " for registration as a dealer for BSPS";
      SimpleMailMessage email = new SimpleMailMessage();
      email.setSubject("BSPS Confirmation");
      email.setTo(user.getEmail());
      email.setText(message);
      email.setFrom(this.env.getProperty("support.email"));
      return email;
   }
}