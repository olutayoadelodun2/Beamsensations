package com.editay.bsps.security.services;

import com.editay.bsps.models.User;
import com.editay.bsps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
   UserRepository userRepository;

   @Transactional
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = (User)this.userRepository.findByUsername(username).orElseThrow(() -> {
         return new UsernameNotFoundException("User Not Found with username: " + username);
      });
      return UserDetailsImpl.build(user);
   }
}
