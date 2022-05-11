/* Decompiler 42ms, total 1506ms, lines 310 */
package com.editay.bsps.controllers;

import com.editay.bsps.dto.UserDto;
import com.editay.bsps.models.ERole;
import com.editay.bsps.models.FreeAdCarDetails;
import com.editay.bsps.models.Role;
import com.editay.bsps.models.User;
import com.editay.bsps.payload.request.SignupRequest;
import com.editay.bsps.payload.response.MessageResponse;
import com.editay.bsps.repository.FreeAdCarDetailsRepository;
import com.editay.bsps.repository.FreeAdRepository;
import com.editay.bsps.repository.RoleRepository;
import com.editay.bsps.repository.UserRepository;
import com.editay.bsps.service.FreeAdCarDetailsService;
import com.editay.bsps.service.UserService;
import com.editay.bsps.service.UserServiceImpl;
import com.editay.bsps.utility.MailConstructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 3600L)
@RestController
@RequestMapping({ "/api/users" })
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private MailConstructor mailConstructor;
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private FreeAdCarDetailsService freeAdCarDetailsService;

	@Autowired
	FreeAdCarDetailsRepository freeAdCarDetailsRepository;

	@Autowired
	FreeAdRepository freeAdRepository;

	private Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Direction.ASC;
		} else {
			return direction.equals("desc") ? Direction.DESC : Direction.ASC;
		}
	}

	@GetMapping({ "/sorteddealers" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllDealers(@RequestParam(defaultValue = "id,desc") String[] sort) {
		try {
			List<Order> orders = new ArrayList();
			if (sort[0].contains(",")) {
				String[] var3 = sort;
				int var4 = sort.length;

				for (int var5 = 0; var5 < var4; ++var5) {
					String sortOrder = var3[var5];
					String[] _sort = sortOrder.split(",");
					orders.add(new Order(this.getSortDirection(_sort[1]), _sort[0]));
				}
			} else {
				orders.add(new Order(this.getSortDirection(sort[1]), sort[0]));
			}

			List<User> users = this.userRepository.findAll(Sort.by(orders));
			return users.isEmpty() ? new ResponseEntity(HttpStatus.NO_CONTENT)
					: new ResponseEntity(users, HttpStatus.OK);
		} catch (Exception var8) {
			return new ResponseEntity((MultiValueMap) null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping({ "/dealers" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getAllUsersPage(@RequestParam(required = false) String surname,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id,desc") String[] sort) {
		try {
			List<Order> orders = new ArrayList();
			if (sort[0].contains(",")) {
				String[] var6 = sort;
				int var7 = sort.length;

				for (int var8 = 0; var8 < var7; ++var8) {
					String sortOrder = var6[var8];
					String[] _sort = sortOrder.split(",");
					orders.add(new Order(this.getSortDirection(_sort[1]), _sort[0]));
				}
			} else {
				orders.add(new Order(this.getSortDirection(sort[1]), sort[0]));
			}

			new ArrayList();
			Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
			Page pageTuts;
			if (surname == null) {
				pageTuts = this.userRepository.findAll(pagingSort);
			} else {
				pageTuts = (Page) this.userRepository.findBySurnameContaining(surname, pagingSort);
			}

			List<User> users = pageTuts.getContent();
			Map<String, Object> response = new HashMap();
			response.put("tutorials", users);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());
			return new ResponseEntity(response, HttpStatus.OK);
		} catch (Exception var11) {
			return new ResponseEntity((MultiValueMap) null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping({ "/dealers/{id}" })
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		Optional<User> tutorialData = this.userRepository.findById(id);
		return tutorialData.isPresent() ? new ResponseEntity(tutorialData.get(), HttpStatus.OK)
				: new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@PostMapping({ "/admin/signup" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (this.userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		} else if (this.userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		} else {
			User user = new User(signUpRequest.getFirstname(), signUpRequest.getMiddlename(),
					signUpRequest.getSurname(), signUpRequest.getPhonenumber(), signUpRequest.getMobile(),
					signUpRequest.getAddress(), signUpRequest.getCity(), signUpRequest.getState(),
					signUpRequest.getZipcode(), signUpRequest.getUsername(), signUpRequest.getTitle(),
					signUpRequest.getEmail(), this.encoder.encode(signUpRequest.getPassword()));
			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet();
			if (strRoles == null) {
				Role userRole = (Role) this.roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> {
					return new RuntimeException("Error: Role is not found.");
				});
				roles.add(userRole);
			} else {
				strRoles.forEach((role) -> {
					byte var4 = -1;
					switch (role.hashCode()) {
					case 92668751:
						if (role.equals("admin")) {
							var4 = 0;
						}
					default:
						switch (var4) {
						case 0:
							Role adminRole = (Role) this.roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> {
								return new RuntimeException("Error: Role is not found.");
							});
							roles.add(adminRole);
							break;
						default:
							Role userRole = (Role) this.roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> {
								return new RuntimeException("Error: Role is not found.");
							});
							roles.add(userRole);
						}

					}
				});
			}

			user.setRoles(roles);
			this.userRepository.save(user);
			return ResponseEntity.ok(
					new MessageResponse("User registered successfully!.Kindly login to continue with subscription"));
		}
	}

	@PutMapping({ "/dealers/{id}" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> updateAdminUser(@PathVariable("id") long id, @RequestBody User user) {
		Optional<User> adminUserData = this.userRepository.findById(id);
		if (adminUserData.isPresent()) {
			if (this.userRepository.existsByEmail(user.getEmail())) {
				User _adminUser = (User) adminUserData.get();
				_adminUser.setFirstname(user.getFirstname());
				_adminUser.setMiddlename(user.getMiddlename());
				_adminUser.setSurname(user.getSurname());
				_adminUser.setPhonenumber(user.getPhonenumber());
				_adminUser.setUsername(user.getUsername());
				_adminUser.setMobile(user.getMobile());
				_adminUser.setAddress(user.getAddress());
				_adminUser.setCity(user.getCity());
				_adminUser.setState(user.getState());
				_adminUser.setZipcode(user.getZipcode());
				_adminUser.setTitle(user.getTitle());
				_adminUser.setEmail(user.getEmail());
				return new ResponseEntity(this.userRepository.save(_adminUser), HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping({ "/dealers/{id}" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteDealers(@PathVariable("id") long id) {
		try {
			this.userRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception var4) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping({ "/delete" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			this.userRepository.deleteAll();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception var2) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = { "http://beamsensations.com" })
	@PutMapping(value = { "/user/{id}" }, produces = { "application/json" }, consumes = { "application/json" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<SignupRequest> updateUser(@RequestBody SignupRequest userR, @PathVariable("id") long id)
			throws Exception {
		SignupRequest signupRequest = this.userServiceImpl.update(userR, id);
		return ResponseEntity.ok().body(signupRequest);
	}

	@CrossOrigin(origins = { "http://beamsensations.com" })
	@GetMapping({ "/user" })
	public ResponseEntity<List<SignupRequest>> listAll() {
		List<SignupRequest> signupRequestList = this.userServiceImpl.getAll();
		return ResponseEntity.ok().body(signupRequestList);
	}

	@CrossOrigin(origins = { "http://beamsensations.com" })
	@DeleteMapping({ "/user/{id}" })
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Boolean> deleteUser(@PathVariable long id) throws Exception {
		return this.userServiceImpl.delete(id);
	}

	@CrossOrigin(origins = { "http://beamsensations.com" })
	@GetMapping(value = { "/user/{id}" }, produces = { "application/json" })
	public ResponseEntity<SignupRequest> findById(@PathVariable long id) throws Exception {
		SignupRequest signupRequest = this.userServiceImpl.getById(id);
		return ResponseEntity.ok().body(signupRequest);
	}

	@CrossOrigin(origins = { "http://beamsensations.com" })
	@GetMapping(value = { "/user/{username}" }, produces = { "application/json" })
	public ResponseEntity<SignupRequest> findByUsername(@PathVariable String username) throws Exception {
		SignupRequest signupRequest = this.userServiceImpl.getByUsername(username);
		return ResponseEntity.ok().body(signupRequest);
	}

	@GetMapping({ "/username" })
	public String getByUsername(@RequestParam String username,
			@RequestHeader(name = "Authorization") String authHeader) {

		System.out.println("Header value is: " + authHeader);
		JSONObject obj2 = new JSONObject();

		try {

			String getUser = ClientController.getASingleSingleUserByAdmin(username, authHeader).replace("[", "")
					.replace("]", "");
			JSONObject obj3 = new JSONObject(getUser);
			System.out.println("obj3: " + obj3);

			obj2.put("id", obj3.getInt("id"));
			obj2.put("username", obj3.getString("username"));
			obj2.put("email", obj3.getString("email"));
			obj2.put("firstname", obj3.getString("firstname"));
			obj2.put("middlename", obj3.getString("middlename"));
			obj2.put("surname", obj3.getString("surname"));
			obj2.put("phonenumber", obj3.getString("phonenumber"));
			obj2.put("mobile", obj3.getString("mobile"));
			obj2.put("address", obj3.getString("address"));
			obj2.put("city", obj3.getString("city"));
			obj2.put("state", obj3.getString("state"));
			obj2.put("zipcode", obj3.getString("zipcode"));
			obj2.put("title", obj3.getString("title"));
			obj2.put("rolename", obj3.getJSONObject("roles").getString("name"));
			System.out.println("obj2: " + obj2);

		} catch (Exception ex) {

			obj2.put("error", ex.getMessage());

		}

		return obj2.toString();
	}

	@GetMapping({ "/usernamebk" })
	public String getByUsernameBk(@RequestParam String usernamebk) {

		ResponseEntity<Optional<User>> result = new ResponseEntity(this.userRepository.findByUsername3(usernamebk),
				HttpStatus.OK);
		JSONObject obj2 = new JSONObject();
		JSONObject obj3 = new JSONObject();

		try {
			JSONObject obj = new JSONObject(result);
			System.out.println("initial response: " + obj);
			String result2 = obj.getString("body");
			System.out.println("required response: " + result2);

			String result3 = result2.replace("Optional[User(", "").replace("[Role", "").replace("])]", "").replace("=",
					":");
			System.out.println("result3: " + result3);
			String result6 = result3.replaceAll("\\w+", "\"$0\"").replace("\"@\"gmail", "@gmail")
					.replace(".\"com\"", ".com\"").replace(".\"org\"", ".org\"").replace("\".\"", ".")
					.replace(".,", "\",");
			System.out.println("result 6: " + result6);
			String result4 = "{" + result6 + "}";
			System.out.println("final required response: " + result4);
			JSONObject obj4 = new JSONObject(result4);
			System.out.println("final required JSON response: " + obj4);

			obj2.put("id", obj4.getInt("id"));
			obj2.put("username", obj4.getString("email"));
			obj2.put("email", obj4.getString("email"));
			obj2.put("firstname", obj4.getString("firstname"));
			obj2.put("middlename", obj4.getString("middlename"));
			obj2.put("surname", obj4.getString("surname"));
			obj2.put("phonenumber", obj4.getString("phonenumber"));
			obj2.put("mobile", obj4.getString("mobile"));
			obj2.put("address", obj4.getString("address"));
			obj2.put("city", obj4.getString("city"));
			obj2.put("state", obj4.getString("state"));
			obj2.put("zipcode", obj4.getString("zipcode"));
			obj2.put("title", obj4.getString("title"));
			obj2.put("roles", obj4.getJSONArray("roles").getString(0));

			System.out.println("final response: " + obj2);

		} catch (Exception ex) {

			obj2.put("error", ex.getStackTrace());
		}

		return obj2.toString();
		// return new ResponseEntity(this.userRepository.findByUsername(username),
		// HttpStatus.OK);
	}

	@GetMapping({ "/username3" })
	public ResponseEntity<Optional<User>> getByUsername3(@RequestParam String username) {

		return new ResponseEntity(this.userRepository.findByUsername3(username), HttpStatus.OK);
	}

	@GetMapping({ "/username2" })
	public ResponseEntity<List<UserDto>> getByUsername2(@RequestParam String username) {
		return new ResponseEntity<List<UserDto>>(userService.findByUsername2(username), HttpStatus.OK);
	}

	@GetMapping({ "/details" })
	public String getDetailsOfDealers(@RequestParam String username, @RequestParam String peakId,
			@RequestParam String token) {
		ClientController pc = new ClientController();
		String result = pc.getDealersFullDetails(username, peakId, token);
		System.out.println(result);
		return result;
	}

	@DeleteMapping({ "/{freeAdId}" })
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<FreeAdCarDetails> deleteFreeAd(@PathVariable("freeAdId") Long freeAdId,
			@RequestHeader(name = "Authorization") String authHeader) {

		long id = FreeAdClientController.getFreeAdSubscriptionIdFromCarUploadDetails(freeAdId, authHeader);
		System.out.println("userCarUploadDetails id:  " + id);
		FreeAdClientController fc = new FreeAdClientController();

		String delete = fc.deleteFreeAdCarAndSubscription2(id, authHeader);
		System.out.println("delete 1: " + delete);
		this.freeAdCarDetailsService.deleteFreeAdCarDetails(freeAdId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}