package com.editay.bsps.security.services;


import com.editay.bsps.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
   private static final long serialVersionUID = 1L;
   private Long id;
   private String first;
   private String middlename;
   private String surname;
   private String phonenumber;
   private String mobile;
   private String address;
   private String city;
   private String state;
   private String zipcode;
   private String username;
   private String title;
   private String email;
   private String fileName;
   @JsonIgnore
   private String password;
   private Collection<? extends GrantedAuthority> authorities;

   public UserDetailsImpl(Long id, String first, String middlename, String surname, String phonenumber, String mobile, String address, String city, String state, String zipcode, String username, String title, String email, String password, Collection<? extends GrantedAuthority> authorities) {
      this.id = id;
      this.first = first;
      this.middlename = middlename;
      this.surname = surname;
      this.phonenumber = phonenumber;
      this.mobile = mobile;
      this.address = address;
      this.city = city;
      this.state = state;
      this.zipcode = zipcode;
      this.username = username;
      this.title = title;
      this.email = email;
      this.password = password;
      this.fileName = this.fileName;
      this.authorities = authorities;
   }

   public static UserDetailsImpl build(User user) {
      List<GrantedAuthority> authorities = (List)user.getRoles().stream().map((role) -> {
         return new SimpleGrantedAuthority(role.getName().name());
      }).collect(Collectors.toList());
      return new UserDetailsImpl(user.getId(), user.getFirstname(), user.getMiddlename(), user.getSurname(), user.getPhonenumber(), user.getMobile(), user.getAddress(), user.getCity(), user.getState(), user.getZipcode(), user.getUsername(), user.getTitle(), user.getEmail(), user.getPassword(), authorities);
   }

   public Collection<? extends GrantedAuthority> getAuthorities() {
      return this.authorities;
   }

   public Long getId() {
      return this.id;
   }

   public String getFirst() {
      return this.first;
   }

   public String getEmail() {
      return this.email;
   }

   public String getUsername() {
      return this.username;
   }

   public String getPassword() {
      return this.password;
   }

   public static long getSerialversionuid() {
      return 1L;
   }

   public String getMiddlename() {
      return this.middlename;
   }

   public String getSurname() {
      return this.surname;
   }

   public String getPhonenumber() {
      return this.phonenumber;
   }

   public String getMobile() {
      return this.mobile;
   }

   public String getAddress() {
      return this.address;
   }

   public String getCity() {
      return this.city;
   }

   public String getState() {
      return this.state;
   }

   public String getZipcode() {
      return this.zipcode;
   }

   public String getTitle() {
      return this.title;
   }

   public String getFileName() {
      return this.fileName;
   }

   public boolean isAccountNonExpired() {
      return true;
   }

   public boolean isAccountNonLocked() {
      return true;
   }

   public boolean isCredentialsNonExpired() {
      return true;
   }

   public boolean isEnabled() {
      return true;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         UserDetailsImpl user = (UserDetailsImpl)o;
         return Objects.equals(this.id, user.id);
      } else {
         return false;
      }
   }
}
