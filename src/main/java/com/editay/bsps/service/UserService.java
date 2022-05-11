package com.editay.bsps.service;


import com.editay.bsps.dto.UserDto;
import com.editay.bsps.models.User;
import com.editay.bsps.payload.request.SignupRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
   SignupRequest update(SignupRequest signupRequest, long id) throws Exception;

   SignupRequest getById(long id) throws Exception;

   SignupRequest getByUsername(String username) throws Exception;

   List<SignupRequest> getAll();

   Map<String, Boolean> delete(long id) throws Exception;
   
   List<UserDto> findByUsername2(String username);
   
   
}
