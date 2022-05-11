/* Decompiler 548ms, total 1474ms, lines 538 */
package com.editay.bsps.controllers;

import com.editay.bsps.models.ERole;
import com.editay.bsps.models.Role;
import com.editay.bsps.models.User;
import com.editay.bsps.payload.request.LoginRequest;
import com.editay.bsps.payload.request.SignupRequest;
import com.editay.bsps.payload.response.JwtResponse;
import com.editay.bsps.payload.response.MessageResponse;
import com.editay.bsps.repository.RoleRepository;
import com.editay.bsps.repository.UserRepository;
import com.editay.bsps.security.jwt.JwtUtils;
import com.editay.bsps.security.services.UserDetailsImpl;
import com.editay.bsps.utility.MailConstructor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 3600L)
@RestController
@RequestMapping({ "/api/auth" })
public class AuthController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private MailConstructor mailConstructor;
	@Autowired
	private JavaMailSender mailSender;

	@PostMapping({ "/signin" })
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = this.jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = (List) userDetails.getAuthorities().stream().map((item) -> {
			return item.getAuthority();
		}).collect(Collectors.toList());
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping({ "/admin/signup" })
	public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupRequest signUpRequest) {
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
							Role adminRole2 = (Role) this.roleRepository.findByName(ERole.ROLE_ADMIN)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(adminRole2);
						}

					}
				});
			}

			user.setRoles(roles);
			this.userRepository.save(user);
			//SimpleMailMessage email = this.mailConstructor.constructCongratulatoryEmail(user);
			//this.mailSender.send(email);
			return ResponseEntity.ok(new MessageResponse(
					"Admin user registered successfully!.Kindly login to continue with subscription"));
		}
	}

	@PostMapping({ "/bulk-dealer/signup" })
	public ResponseEntity<?> registerBulkDealerUser(@Valid @RequestBody SignupRequest signUpRequest) {
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
				Role userRole = (Role) this.roleRepository.findByName(ERole.ROLE_BULK_DEALER).orElseThrow(() -> {
					return new RuntimeException("Error: Role is not found.");
				});
				roles.add(userRole);
			} else {
				strRoles.forEach((role) -> {
					byte var4 = -1;
					switch (role.hashCode()) {
					case -1712079052:
						if (role.equals("bulk-dealer")) {
							var4 = 0;
						}
					default:
						switch (var4) {
						case 0:
							Role bulkDealerRole = (Role) this.roleRepository.findByName(ERole.ROLE_BULK_DEALER)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(bulkDealerRole);
							break;
						default:
							Role bulkDealerRole2 = (Role) this.roleRepository.findByName(ERole.ROLE_BULK_DEALER)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(bulkDealerRole2);
						}

					}
				});
			}

			user.setRoles(roles);
			this.userRepository.save(user);
			//SimpleMailMessage email = this.mailConstructor.constructCongratulatoryEmail(user);
		//	this.mailSender.send(email);
			return ResponseEntity.ok(new MessageResponse(
					"Bulk dealer registered successfully!.Kindly login to continue with subscription"));
		}
	}

	@PostMapping({ "/unit-dealer/signup" })
	public ResponseEntity<?> registerUnitDealerUser(@Valid @RequestBody SignupRequest signUpRequest) {
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
				Role userRole = (Role) this.roleRepository.findByName(ERole.ROLE_UNIT_DEALER).orElseThrow(() -> {
					return new RuntimeException("Error: Role is not found.");
				});
				roles.add(userRole);
			} else {
				strRoles.forEach((role) -> {
					byte var4 = -1;
					switch (role.hashCode()) {
					case -486002046:
						if (role.equals("unit-dealer")) {
							var4 = 0;
						}
					default:
						switch (var4) {
						case 0:
							Role unitDealerRole = (Role) this.roleRepository.findByName(ERole.ROLE_UNIT_DEALER)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(unitDealerRole);
							break;
						default:
							Role unitDealerRole2 = (Role) this.roleRepository.findByName(ERole.ROLE_UNIT_DEALER)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(unitDealerRole2);
						}

					}
				});
			}

			user.setRoles(roles);
			this.userRepository.save(user);
			//SimpleMailMessage email = this.mailConstructor.constructCongratulatoryEmail(user);
			//this.mailSender.send(email);
			return ResponseEntity.ok(
					new MessageResponse("User registered successfully!.Kindly login to continue with subscription"));
		}
	}

	@PostMapping({ "/unit-seller/signup" })
	public ResponseEntity<?> registerUnitSellerUser(@Valid @RequestBody SignupRequest signUpRequest) {
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
				Role userRole = (Role) this.roleRepository.findByName(ERole.ROLE_UNIT_SELLER).orElseThrow(() -> {
					return new RuntimeException("Error: Role is not found.");
				});
				roles.add(userRole);
			} else {
				strRoles.forEach((role) -> {
					byte var4 = -1;
					switch (role.hashCode()) {
					case -56237080:
						if (role.equals("unit-seller")) {
							var4 = 0;
						}
					default:
						switch (var4) {
						case 0:
							Role unitSellerRole = (Role) this.roleRepository.findByName(ERole.ROLE_UNIT_SELLER)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(unitSellerRole);
							break;
						default:
							Role unitSellerRole2 = (Role) this.roleRepository.findByName(ERole.ROLE_UNIT_SELLER)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(unitSellerRole2);
						}

					}
				});
			}

			user.setRoles(roles);
			this.userRepository.save(user);
			//SimpleMailMessage email = this.mailConstructor.constructCongratulatoryEmail(user);
			//this.mailSender.send(email);
			return ResponseEntity.ok(new MessageResponse(
					"Unit seller registered successfully!.Kindly login to continue with subscription"));
		}
	}

	@PostMapping({ "/car-hire/signup" })
	public ResponseEntity<?> registerCarHireUser(@Valid @RequestBody SignupRequest signUpRequest) {
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
				Role userRole = (Role) this.roleRepository.findByName(ERole.ROLE_VENDOR_CAR_HIRE).orElseThrow(() -> {
					return new RuntimeException("Error: Role is not found.");
				});
				roles.add(userRole);
			} else {
				strRoles.forEach((role) -> {
					byte var4 = -1;
					switch (role.hashCode()) {
					case -58440371:
						if (role.equals("car-hire")) {
							var4 = 0;
						}
					default:
						switch (var4) {
						case 0:
							Role carHireRole = (Role) this.roleRepository.findByName(ERole.ROLE_VENDOR_CAR_HIRE)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(carHireRole);
							break;
						default:
							Role carHireRole2 = (Role) this.roleRepository.findByName(ERole.ROLE_VENDOR_CAR_HIRE)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(carHireRole2);
						}

					}
				});
			}

			user.setRoles(roles);
			this.userRepository.save(user);
			//SimpleMailMessage email = this.mailConstructor.constructCongratulatoryEmail(user);
			//this.mailSender.send(email);
			return ResponseEntity.ok(new MessageResponse(
					"Car hire registered successfully!.Kindly login to continue with subscription"));
		}
	}

	@PostMapping({ "/car-lease/signup" })
	public ResponseEntity<?> registerCarLeaseUser(@Valid @RequestBody SignupRequest signUpRequest) {
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
				Role userRole = (Role) this.roleRepository.findByName(ERole.ROLE_VENDR_CAR_LEASE).orElseThrow(() -> {
					return new RuntimeException("Error: Role is not found.");
				});
				roles.add(userRole);
			} else {
				strRoles.forEach((role) -> {
					byte var4 = -1;
					switch (role.hashCode()) {
					case -1808092383:
						if (role.equals("car-lease")) {
							var4 = 0;
						}
					default:
						switch (var4) {
						case 0:
							Role carHireRole = (Role) this.roleRepository.findByName(ERole.ROLE_VENDR_CAR_LEASE)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(carHireRole);
							break;
						default:
							Role carHireRole2 = (Role) this.roleRepository.findByName(ERole.ROLE_VENDR_CAR_LEASE)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(carHireRole2);
						}

					}
				});
			}

			user.setRoles(roles);
			this.userRepository.save(user);
			//SimpleMailMessage email = this.mailConstructor.constructCongratulatoryEmail(user);
			//this.mailSender.send(email);
			return ResponseEntity.ok(new MessageResponse(
					"Car lease registered successfully!.Kindly login to continue with subscription"));
		}
	}

	@PostMapping({ "/brand-promotion/signup" })
	public ResponseEntity<?> registerBrandPromotionUser(@Valid @RequestBody SignupRequest signUpRequest) {
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
				Role userRole = (Role) this.roleRepository.findByName(ERole.ROLE_PROMOTION_BRAND).orElseThrow(() -> {
					return new RuntimeException("Error: Role is not found.");
				});
				roles.add(userRole);
			} else {
				strRoles.forEach((role) -> {
					byte var4 = -1;
					switch (role.hashCode()) {
					case -1822904867:
						if (role.equals("brand-promotion")) {
							var4 = 0;
						}
					default:
						switch (var4) {
						case 0:
							Role carHireRole = (Role) this.roleRepository.findByName(ERole.ROLE_PROMOTION_BRAND)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(carHireRole);
							break;
						default:
							Role carHireRole2 = (Role) this.roleRepository.findByName(ERole.ROLE_PROMOTION_BRAND)
									.orElseThrow(() -> {
										return new RuntimeException("Error: Role is not found.");
									});
							roles.add(carHireRole2);
						}

					}
				});
			}

			user.setRoles(roles);
			this.userRepository.save(user);
			//SimpleMailMessage email = this.mailConstructor.constructCongratulatoryEmail(user);
			//this.mailSender.send(email);
			return ResponseEntity.ok(new MessageResponse(
					"Brand promotion registered successfully!.Kindly login to continue with subscription"));
		}
	}

	@PostMapping({ "/signup" })
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
					case -1822904867:
						if (role.equals("brand-promotion")) {
							var4 = 1;
						}
						break;
					case -1808092383:
						if (role.equals("car-lease")) {
							var4 = 5;
						}
						break;
					case -1712079052:
						if (role.equals("bulk-dealer")) {
							var4 = 6;
						}
						break;
					case -486002046:
						if (role.equals("unit-dealer")) {
							var4 = 2;
						}
						break;
					case -58440371:
						if (role.equals("car-hire")) {
							var4 = 4;
						}
						break;
					case -56237080:
						if (role.equals("unit-seller")) {
							var4 = 3;
						}
						break;
					case 92668751:
						if (role.equals("admin")) {
							var4 = 0;
						}
					}

					switch (var4) {
					case 0:
						Role adminRole = (Role) this.roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> {
							return new RuntimeException("Error: Role is not found.");
						});
						roles.add(adminRole);
						break;
					case 1:
						Role brandPromotionRole = (Role) this.roleRepository.findByName(ERole.ROLE_PROMOTION_BRAND)
								.orElseThrow(() -> {
									return new RuntimeException("Error: Role is not found.");
								});
						roles.add(brandPromotionRole);
						break;
					case 2:
						Role unitDealerRole = (Role) this.roleRepository.findByName(ERole.ROLE_UNIT_DEALER)
								.orElseThrow(() -> {
									return new RuntimeException("Error: Role is not found.");
								});
						roles.add(unitDealerRole);
						break;
					case 3:
						Role unitSellerRole = (Role) this.roleRepository.findByName(ERole.ROLE_UNIT_SELLER)
								.orElseThrow(() -> {
									return new RuntimeException("Error: Role is not found.");
								});
						roles.add(unitSellerRole);
						break;
					case 4:
						Role carHireRole = (Role) this.roleRepository.findByName(ERole.ROLE_VENDOR_CAR_HIRE)
								.orElseThrow(() -> {
									return new RuntimeException("Error: Role is not found.");
								});
						roles.add(carHireRole);
						break;
					case 5:
						Role carLeaseRole = (Role) this.roleRepository.findByName(ERole.ROLE_VENDR_CAR_LEASE)
								.orElseThrow(() -> {
									return new RuntimeException("Error: Role is not found.");
								});
						roles.add(carLeaseRole);
						break;
					case 6:
						Role bulkDealerRole = (Role) this.roleRepository.findByName(ERole.ROLE_BULK_DEALER)
								.orElseThrow(() -> {
									return new RuntimeException("Error: Role is not found.");
								});
						roles.add(bulkDealerRole);
						break;
					default:
						Role userRole = (Role) this.roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> {
							return new RuntimeException("Error: Role is not found.");
						});
						roles.add(userRole);
					}

				});
			}

			user.setRoles(roles);
			this.userRepository.save(user);
			//SimpleMailMessage email = this.mailConstructor.constructCongratulatoryEmail(user);
			//this.mailSender.send(email);
			return ResponseEntity.ok(
					new MessageResponse("User registered successfully!.Kindly login to continue with subscription"));
		}
	}
}