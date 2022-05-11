/* Decompiler 8ms, total 966ms, lines 118 */
package com.editay.bsps.controllers;

import com.editay.bsps.dto.FreeAdUpdateDto;
import com.editay.bsps.models.FreeAd;
import com.editay.bsps.models.FreeAdCarDetails;
import com.editay.bsps.models.PeakAd;
import com.editay.bsps.models.User;
import com.editay.bsps.repository.FreeAdRepository;
import com.editay.bsps.repository.UserRepository;
import com.editay.bsps.service.FreeAdService;
import com.editay.bsps.service.PaystackInitializeTransactionService;
import com.editay.bsps.utility.CommonUtil;
import com.editay.bsps.utility.JWTDecoder;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 3600L)
@RestController
@RequestMapping({ "/api/freeAds" })
public class FreeAdController {
	FreeAdService freeAdService;
	@Autowired
	FreeAdRepository freeAdRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private PaystackInitializeTransactionService initializeTransactionService;

	public FreeAdController(FreeAdService freeAdService) {
		this.freeAdService = freeAdService;
	}

	@GetMapping
	public ResponseEntity<List<FreeAd>> getAllFreeAds() {
		List<FreeAd> freeAds = this.freeAdService.getFreeAds();
		return new ResponseEntity(freeAds, HttpStatus.OK);
	}

	@GetMapping({ "/approved" })
	public ResponseEntity<List<FreeAd>> getAllApprovedFreeAds() {
		List<FreeAd> freeAds = this.freeAdService.getAllApprovedFreeAds();
		return new ResponseEntity(freeAds, HttpStatus.OK);
	}

	@GetMapping({ "/unapproved" })
	public ResponseEntity<List<FreeAd>> getAllUnapprovedFreeAds() {
		List<FreeAd> freeAds = this.freeAdService.getAllUnapprovedFreeAds();
		return new ResponseEntity(freeAds, HttpStatus.OK);
	}

	@GetMapping({ "/rejected" })
	public ResponseEntity<List<FreeAd>> getAllRejectedFreeAds() {
		List<FreeAd> freeAds = this.freeAdService.getAllRejectedFreeAds();
		return new ResponseEntity(freeAds, HttpStatus.OK);
	}

	@GetMapping({ "/completed" })
	public ResponseEntity<List<FreeAd>> getAllCompletedFreeAds() {
		List<FreeAd> freeAds = this.freeAdService.getAllCompletedFreeAds();
		return new ResponseEntity(freeAds, HttpStatus.OK);
	}

	@GetMapping({ "/publishASingleFreead/{id}" })
	public FreeAd getPublishedFreeAd(@PathVariable int id) {
		FreeAd freeAdResponse = freeAdService.publishApprovedFreeadById(id);
		return freeAdResponse;
	}

	@GetMapping({ "/{freeAdId}" })

	public ResponseEntity<FreeAd> getFreeAd(@PathVariable Long freeAdId) {
		return new ResponseEntity(this.freeAdService.getFreeAdById(freeAdId), HttpStatus.OK);
	}

	@GetMapping({ "/publishFreead/{freeAdId}" })
	public ResponseEntity<FreeAd> publishFreeAd(@PathVariable String freeAdId) {
		return new ResponseEntity(this.freeAdService.publishedFreeAd(freeAdId), HttpStatus.OK);
	}

	@GetMapping({ "/publishFreead2/{id}" })
	public String publishFreeAd2(@PathVariable int id) {
		ClientController cc = new ClientController();
		return cc.publishFreeadCarupload(id);
	}

	@PostMapping({ "/freeAd" })
	// @PreAuthorize("hasRole('ADMIN')")

	public ResponseEntity<FreeAd> saveFreeAd(@RequestBody FreeAd freeAd,

			@RequestHeader(name = "Authorization") String authHeader) {

		System.out.println("Header value is: " + authHeader);
		String token = JWTDecoder.token(authHeader);
		String username = JWTDecoder.findTokenUsername(token);
		freeAd.setUsername(username);
		freeAd.setNoOfDays(3);
		freeAd.setFreeDescription(freeAd.getNoOfDays() + " free.");
		freeAd.setSubscriptionDate(freeAd.getDateCreated());
		freeAd.setIsUsed("N");
		/*
		 * freeAd.setSubscriptionExpireDate(
		 * CommonUtil.expireDate(freeAd.getSubscriptionDate().toString(), (int)
		 * freeAd.getNoOfDays()));
		 */

		/*
		 * freeAd.setSubscriptionExpireDate(
		 * CommonUtil.expireDate(CommonUtil.convertToDate(freeAd.getSubscriptionDate().
		 * toString()), (int) freeAd.getNoOfDays()));
		 */

		freeAd.setSubscriptionExpireDate("Your subscription we expired in " + freeAd.getNoOfDays());
		freeAd.setAccountStatus("UNAPPROVED");
		System.out.println("freeAd.getAccountStatus() :" + freeAd.getAccountStatus());
		System.out.println("freeAd.getFreeAdId() :" + freeAd.getFreeAdId());
		System.out.println("freeAd.getFreeDescription() :" + freeAd.getFreeDescription());
		System.out.println("freeAd.getNoOfDays() :" + freeAd.getNoOfDays());
		System.out.println("freeAd.getSubscriptionDate() :" + freeAd.getSubscriptionDate());
		System.out.println("freeAd.getSubscriptionExpireDate() :" + freeAd.getSubscriptionExpireDate());
		System.out.println("freeAd.getUsername() :" + freeAd.getUsername());
		System.out.println("freeAd.getDateCreated() :" + freeAd.getDateCreated());
		System.out.println("freeAd.getLastModified() :" + freeAd.getLastModified());
		FreeAd freeAd1 = this.freeAdService.insert(freeAd);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("freeAd", "/api/freeAds" + Long.toString(freeAd1.getId()));
		return new ResponseEntity(freeAd1, httpHeaders, HttpStatus.CREATED);
	}
	
	

	   @PreAuthorize("hasRole('ADMIN')")
		@PutMapping("/approveorreject/update/{id}/{accountStatus}")
		public ResponseEntity<String> updateAccountStatus(@PathVariable int id, @PathVariable String accountStatus) {
			
			System.out.println("START UPDATING..........");
			//Long l= new Long(id);  
			//int i=l.intValue();  
			return new ResponseEntity<String>(freeAdRepository.updateAccountStatus(accountStatus,id) + " record(s)" + accountStatus + ".", HttpStatus.OK);
		}
		
		
	@PutMapping({ "/update/status2/{freeAdId}" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<FreeAd> updateFreeAd2(@PathVariable("freeAdId") Long freeAdId, @RequestBody FreeAd freeAd,
			@RequestHeader(name = "Authorization") String authHeader) {
		this.freeAdService.updateFreeAdAfterPayment(freeAdId, freeAd);

		System.out.println("Header value is: " + authHeader);
		String token = JWTDecoder.token(authHeader);
		String username = JWTDecoder.findTokenUsername(token);
		freeAd.setUsername(username);
		freeAd.setNoOfDays(3);
		freeAd.setFreeDescription(freeAd.getNoOfDays() + " free.");
		freeAd.setSubscriptionDate(freeAd.getDateCreated());

		freeAd.setSubscriptionExpireDate("Your subscription we expired in " + freeAd.getNoOfDays());
		freeAd.setAccountStatus("APPROVED");
		System.out.println("freeAd.getAccountStatus() :" + freeAd.getAccountStatus());
		System.out.println("freeAd.getFreeAdId() :" + freeAd.getFreeAdId());
		System.out.println("freeAd.getFreeDescription() :" + freeAd.getFreeDescription());
		System.out.println("freeAd.getNoOfDays() :" + freeAd.getNoOfDays());
		System.out.println("freeAd.getSubscriptionDate() :" + freeAd.getSubscriptionDate());
		System.out.println("freeAd.getSubscriptionExpireDate() :" + freeAd.getSubscriptionExpireDate());
		System.out.println("freeAd.getUsername() :" + freeAd.getUsername());
		System.out.println("freeAd.getDateCreated() :" + freeAd.getDateCreated());
		System.out.println("freeAd.getLastModified() :" + freeAd.getLastModified());

		return new ResponseEntity(this.freeAdService.getFreeAdById(freeAdId), HttpStatus.OK);
	}

	@PutMapping({ "/update/{freeAdId}" })
	public ResponseEntity<FreeAd> dealerUpdateFreeAd(@PathVariable("freeAdId") Long freeAdId,
			@RequestBody FreeAd freeAd) {
		this.freeAdService.updateFreeAd(freeAdId, freeAd);
		return new ResponseEntity(this.freeAdService.getFreeAdById(freeAdId), HttpStatus.OK);
	}

	@PutMapping({ "/update/status/{freeAdId}" })
	public ResponseEntity<FreeAd> dealerUpdateFreeAd2(@PathVariable("freeAdId") Long freeAdId,
			@RequestBody FreeAd freeAd) {
		freeAd.setAccountStatus("APPROVED");
		this.freeAdService.updateFreeAdForAccountStatus(freeAdId, freeAd);
		return new ResponseEntity(this.freeAdService.getFreeAdById(freeAdId), HttpStatus.OK);
	}

	@DeleteMapping({ "/{freeAdId}" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<FreeAd> deleteFreeAd(@PathVariable("freeAdId") Long freeAdId) {
		this.freeAdService.deleteFreeAd(freeAdId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	@DeleteMapping("/users/{freeAdId}")
	
	public ResponseEntity<FreeAd> userDeleteFreeAd(@PathVariable("freeAdId") Long freeAdId) {
		this.freeAdService.deleteFreeAd(freeAdId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/caruploads/{id}")
	public ResponseEntity<FreeAd> getFreeAdCarDetailsById(@PathVariable("id") long id) {
		Optional<FreeAd> freeAdCarDetailsData = freeAdRepository.findById((long) id);
		if (freeAdCarDetailsData.isPresent()) {
			return new ResponseEntity<>(freeAdCarDetailsData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	@GetMapping({ "/freeId" })
	public ResponseEntity<List<FreeAd>> getFreeIdByName(@RequestParam String freeId) {
		return null;
	}

	@GetMapping({ "/user" })
	public ResponseEntity<Optional<User>> getByUsername(@RequestParam String username) {
		return new ResponseEntity(this.userRepository.findByUsername(username), HttpStatus.OK);
	}

	@GetMapping({ "/username" })
	public ResponseEntity<List<FreeAd>> getFreeByUsername(@RequestParam String username) {
		return new ResponseEntity(this.freeAdRepository.findByUsername(username), HttpStatus.OK);
	}

	@GetMapping({ "/freead" })
	public ResponseEntity<List<FreeAd>> getFreeAdByIdAndAccountStatus(@RequestParam String freeAdId) {
		return new ResponseEntity(this.freeAdRepository.findByAccountStatusAndId(freeAdId), HttpStatus.OK);
	}

	@RequestMapping(value = "/freead/{freeAdId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<FreeAd>> getFreeAdByIdAndAccountStatusWhenNotApproved(@PathVariable String freeAdId) {
		return new ResponseEntity(this.freeAdRepository.findByAccountStatusAndIdWhenNotApproved(freeAdId),
				HttpStatus.OK);
	}

	@GetMapping({ "/freead2" })
	public ResponseEntity<List<FreeAd>> checkIfApproved(@RequestParam String freeAdId) {
		return new ResponseEntity(this.freeAdRepository.findIfApproved(freeAdId), HttpStatus.OK);
	}

	@GetMapping("/freeads/{id}")
	public ResponseEntity<FreeAd> getFreeAdById(@PathVariable("id") long id) {
		Optional<FreeAd> freeAdData = freeAdRepository.findById(id);
		if (freeAdData.isPresent()) {
			return new ResponseEntity<>(freeAdData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping({ "/email" })
	public ResponseEntity<List<User>> getFreeByEmail(@RequestParam String email) {
		return new ResponseEntity(this.userRepository.findByEmail(email), HttpStatus.OK);
	}
}