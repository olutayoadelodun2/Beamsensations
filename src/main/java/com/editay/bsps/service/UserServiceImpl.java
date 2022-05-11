package com.editay.bsps.service;

import com.editay.bsps.dto.UserDto;
import com.editay.bsps.models.User;
import com.editay.bsps.payload.request.SignupRequest;
import com.editay.bsps.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	private User convertRequesttoModel(SignupRequest signupRequest) {
		User user = new User();
		user.setAddress(signupRequest.getAddress());
		user.setCity(signupRequest.getCity());
		user.setEmail(signupRequest.getEmail());
		user.setFirstname(signupRequest.getFirstname());
		user.setMiddlename(signupRequest.getMiddlename());
		user.setMobile(signupRequest.getMobile());
		user.setPhonenumber(signupRequest.getPhonenumber());
		user.setState(signupRequest.getState());
		user.setSurname(signupRequest.getSurname());
		user.setTitle(signupRequest.getTitle());
		user.setUsername(signupRequest.getUsername());
		user.setZipcode(signupRequest.getZipcode());
		return user;
	}

	private SignupRequest convertModelToRequest(User user) {
		return new SignupRequest(user);
	}

	public SignupRequest update(SignupRequest signupRequest, long id) throws Exception {
		SignupRequest signupReq = this.getById(id);
		signupReq.setAddress(signupRequest.getAddress());
		signupReq.setCity(signupRequest.getCity());
		signupReq.setEmail(signupRequest.getEmail());
		signupReq.setFirstname(signupRequest.getFirstname());
		signupReq.setMiddlename(signupRequest.getMiddlename());
		signupReq.setMobile(signupRequest.getMobile());
		signupReq.setPhonenumber(signupRequest.getPhonenumber());
		signupReq.setState(signupRequest.getState());
		signupReq.setSurname(signupRequest.getSurname());
		signupReq.setTitle(signupRequest.getTitle());
		signupReq.setUsername(signupRequest.getUsername());
		signupReq.setZipcode(signupRequest.getZipcode());
		User user = this.convertRequesttoModel(signupReq);
		this.userRepo.save(user);
		return this.convertModelToRequest(user);
	}

	public SignupRequest getById(long id) throws Exception {
		User user = (User) this.userRepo.findById(id).orElseThrow(() -> {
			return new Exception("ID NOT FOUND EXCEPTION :::: " + id);
		});
		return this.convertModelToRequest(user);
	}

	public SignupRequest getByUsername(String username) throws Exception {
		User user = (User) this.userRepo.findByUsername(username).orElseThrow(() -> {
			return new Exception("USERNAME NOT FOUND EXCEPTION :::: " + username);
		});
		return this.convertModelToRequest(user);
	}

	public List<SignupRequest> getAll() {
		List<User> userList = this.userRepo.findAll();
		List<SignupRequest> signupRequestList = new ArrayList();
		Iterator var3 = userList.iterator();

		while (var3.hasNext()) {
			User user = (User) var3.next();
			signupRequestList.add(this.convertModelToRequest(user));
		}

		return signupRequestList;
	}

	public Map<String, Boolean> delete(long id) throws Exception {
		User user = this.convertRequesttoModel(this.getById(id));
		this.userRepo.delete(user);
		Map<String, Boolean> response = new HashMap();
		response.put("Delete", Boolean.TRUE);
		return response;
	}

	@Override
	public List<UserDto> findByUsername2(String username) {
		// TODO Auto-generated method stub
		List<UserDto> list = userRepo.findByUsername2(username);
		list.forEach(l -> System.out.println(l));
		return list;
	}
}
