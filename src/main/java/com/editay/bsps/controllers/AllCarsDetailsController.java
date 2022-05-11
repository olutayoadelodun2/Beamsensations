package com.editay.bsps.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.editay.bsps.models.FreeAdCarDetails;
import com.editay.bsps.repository.FreeAdCarDetailsRepository;
import com.editay.bsps.repository.FreeAdRepository;
import com.editay.bsps.repository.UserRepository;
import com.editay.bsps.service.FreeAdCarDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = { "*" }, maxAge = 3600L)
//@CrossOrigin(origins = { "*" }, maxAge = 3600L)
//@CrossOrigin(origins = {"http://beamsensations.com", "http://localhost:3000"})
@RestController
@RequestMapping({ "/api/allcarsdetails" })
public class AllCarsDetailsController {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(AllCarsDetailsController.class);
	
	
	@Autowired
	private FreeAdCarDetailsService freeAdCarDetailsService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	FreeAdCarDetailsRepository freeAdCarDetailsRepository;
	
	@Autowired
	FreeAdRepository freeAdRepository;
	
	
	@GetMapping({ "/username/{username}" })
	public ResponseEntity<FreeAdCarDetails> usersAllCarDetails(@PathVariable String username) {
		
		LOGGER.debug("username: " + username);
		LOGGER.debug("details: " + this.freeAdCarDetailsRepository.findByUsername(username));
		
		return new ResponseEntity(this.freeAdCarDetailsRepository.findByUsername(username), HttpStatus.OK);
	}

}
