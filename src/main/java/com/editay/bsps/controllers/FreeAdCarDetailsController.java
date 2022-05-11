package com.editay.bsps.controllers;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.editay.bsps.dto.FreeAdUpdateDto;
import com.editay.bsps.models.FreeAd;
import com.editay.bsps.models.FreeAdCarDetails;
import com.editay.bsps.models.PeakAd;
import com.editay.bsps.models.PeakAd2;
import com.editay.bsps.repository.FreeAdCarDetailsRepository;
import com.editay.bsps.repository.FreeAdRepository;
import com.editay.bsps.repository.UserRepository;
import com.editay.bsps.service.*;
import com.editay.bsps.utility.CommonUtil;
import com.editay.bsps.utility.JWTDecoder;

@CrossOrigin(origins = { "*" }, maxAge = 3600L)
//@CrossOrigin(origins = {"http://beamsensations.com", "http://localhost:3000"})
@RestController
@RequestMapping({ "/api/freeAdsCarUpload" })
public class FreeAdCarDetailsController {

	@Autowired
	private FreeAdCarDetailsService freeAdCarDetailsService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	FreeAdCarDetailsRepository freeAdCarDetailsRepository;

	@Autowired
	FreeAdRepository freeAdRepository;

	ClientController cc = new ClientController();

	@GetMapping({ "/username/{username}" })
	public ResponseEntity<FreeAdCarDetails> usersAllCarDetails(@PathVariable String username) {

		return new ResponseEntity(this.freeAdCarDetailsRepository.findByUsername(username), HttpStatus.OK);
	}

	@RequestMapping(value = "/saveUpload", method = RequestMethod.POST)
	@ResponseBody
	public FreeAdCarDetails saveFreeAdCarDetails(@RequestBody FreeAdCarDetails freeAdCarDetails,
			@RequestHeader(name = "Authorization") String authHeader) {

		FreeAdCarDetails freeAdCarDetailsResponse = null;
		ClientController cc = new ClientController();
		System.out.println("car upload authorization header: " + authHeader);
		JSONObject ob = new JSONObject();

		try {

			String isUsedForFreead = FreeAdClientController
					.checkIsUsedByFreeAd2(Integer.parseInt(freeAdCarDetails.getFreeAdId()), authHeader);
			System.out.println("isUsedForFreead: " + isUsedForFreead);

			JSONObject isusedObj = new JSONObject(isUsedForFreead);
			String isUsed = isusedObj.getString("isUsed");
			System.out.println("isUsed: " + isUsed);
			String checkAcountStatus = FreeAdClientController
					.checkIsUsedByFreeAd(Integer.parseInt(freeAdCarDetails.getFreeAdId()), authHeader);
			System.out.println("checkAcountStatus: " + checkAcountStatus);
			JSONObject checkAccountStatusObj = new JSONObject(checkAcountStatus);
			String accountStatus = checkAccountStatusObj.getString("accountStatus");
			System.out.println("accountStatus: " + accountStatus);

			if (isUsed.equalsIgnoreCase("N") && accountStatus.equalsIgnoreCase("UNAPPROVED")) {

				String freeAdDetails = ClientController.getFreeAdById2(Integer.parseInt(freeAdCarDetails.getFreeAdId()),
						authHeader);
				System.out.println("freead subscription details: " + freeAdDetails);

				System.out.println("Header value is: " + authHeader);
				String token = JWTDecoder.token(authHeader);
				String username = JWTDecoder.findTokenUsername(token);
				freeAdCarDetails.setUsername(username);
				freeAdCarDetails.setAccountStatus("UNAPPROVED");

				JSONObject obj = new JSONObject(freeAdDetails);
				String date = obj.getString("subscriptionDate");
				System.out.println("date: " + date);
				String dateConverted = CommonUtil.convertToDate(date);
				System.out.println("date converted: " + dateConverted);
				Timestamp dateInTimestamp = Timestamp.valueOf(dateConverted);

				freeAdCarDetails.setDate(dateInTimestamp);
				freeAdCarDetails.setAccessToken("");
				freeAdCarDetails.setIsUsed("N");

				freeAdCarDetailsResponse = freeAdCarDetailsService.saveFreeAdCarDetails(freeAdCarDetails);

			} else if (isUsed.equals("N") && accountStatus.equalsIgnoreCase("UNAPPROVED")) {

				ob.put("error", "Your free ad subscription has not expired");
				FreeAdCarDetails det = new FreeAdCarDetails(ob.toString());
				freeAdCarDetailsResponse = det;
			} else {

				ob.put("error", "Your free ad subscription has not expired.");
				FreeAdCarDetails det = new FreeAdCarDetails(ob.toString());
				freeAdCarDetailsResponse = det;
			}

		} catch (Exception ex) {

			ob.put("error", ex.getMessage());
			FreeAdCarDetails det = new FreeAdCarDetails(ex.getMessage());
			freeAdCarDetailsResponse = det;

		}

		/*
		 * String freeAdDetails2 = freeAdDetails.replace("[", "").replace("]", "");
		 * System.out.println("freead details 2: " + freeAdDetails2);
		 * 
		 * JSONObject obj = new JSONObject(freeAdDetails2);
		 * System.out.println("freead details 3: " + obj);
		 * 
		 * String username = obj.getString("username"); String accountStatus =
		 * obj.getString("accountStatus");
		 * 
		 * String freead = cc.getFreeAdById(freeAdCarDetails.getFreeAdId(),
		 * freeAdCarDetails.getAccessToken()) .replace("[", "").replace("]", "");
		 * System.out.println("freead details 4: " + freead);
		 * 
		 * JSONObject freeadObj = new JSONObject(freead);
		 * 
		 * if (!freeadObj.getString("freeAdId").isEmpty()) {
		 * 
		 * if (accountStatus.equals("APPROVED") || accountStatus.equals("UNAPPROVED")) {
		 * 
		 * if (username.equals(freeAdCarDetails.getUsername())) {
		 * 
		 * if (!this.userRepository.existsByUsername(username)) {
		 * 
		 * freeAdCarDetailsResponse = null;
		 * 
		 * } else {
		 * 
		 * freeAdCarDetailsResponse =
		 * freeAdCarDetailsService.saveFreeAdCarDetails(freeAdCarDetails); }
		 * 
		 * } else {
		 * 
		 * freeAdCarDetailsResponse = null; }
		 * 
		 * } else {
		 * 
		 * freeAdCarDetailsResponse = null; }
		 * 
		 * } else {
		 * 
		 * freeAdCarDetailsResponse = null; } return freeAdCarDetailsResponse;
		 */

		return freeAdCarDetailsResponse;
	}

	/*
	 * @PutMapping({ "/{freeAdId}" })
	 * 
	 * @PreAuthorize("hasRole('ADMIN')") public ResponseEntity<FreeAd>
	 * updateFreeAd(@PathVariable("freeAdId") Long id, @RequestBody FreeAdUpdateDto
	 * dto,
	 * 
	 * @RequestHeader(name = "Authorization") String authHeader) {
	 * this.freeAdCarDetailsService.updateFreeAdAfterPayment(id,dto);
	 * 
	 * System.out.println("Header value is: " + authHeader); String token =
	 * JWTDecoder.token(authHeader); String username =
	 * JWTDecoder.findTokenUsername(token);
	 * 
	 * //int id2 =
	 * 
	 * 
	 * return new
	 * ResponseEntity(this.freeAdCarDetailsService.getFreeAdCarDetailsById(id),
	 * HttpStatus.OK); }
	 */

	// *********************************************************NEW
	// IMPLEMENTATION**************************************************************

	@GetMapping("/caruploads/{id}")
	public ResponseEntity<FreeAdCarDetails> getFreeAdCarDetailsById(@PathVariable("id") long id) {
		Optional<FreeAdCarDetails> freeAdCarDetailsData = freeAdCarDetailsRepository.findById((long) id);
		if (freeAdCarDetailsData.isPresent()) {
			return new ResponseEntity<>(freeAdCarDetailsData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/freeadUploadDetails/{id}")
	public String getFreeAdCarDetailsById(@PathVariable("id") Long id,
			@RequestHeader(name = "Authorization") String authHeader) {

		ClientController cc = new ClientController();
		JSONObject responseObj = new JSONObject();
		String response = "";

		try {

			String freeadCardetailsById = ClientController.getFreeAdCaruploadById((Long) id, authHeader);
			System.out.println("freeadCardetailsById: " + freeadCardetailsById);
			JSONObject obj = new JSONObject(freeadCardetailsById);
			String freeAdId = obj.getString("freeAdId");
			System.out.println("freeAdId: " + freeAdId);

			String freeAdDetails = ClientController.getFreeAdById2(Integer.parseInt(freeAdId), authHeader);
			JSONObject obj2 = new JSONObject(freeAdDetails);

			responseObj.put("id", obj.getInt("id"));
			responseObj.put("productTitle", obj.getString("productTitle"));
			responseObj.put("bodyType", obj.getString("bodyType"));
			responseObj.put("engineType", obj.getString("engineType"));
			responseObj.put("fuelType", obj.getString("fuelType"));
			responseObj.put("transmission", obj.getString("transmission"));
			responseObj.put("mileage", obj.getString("mileage"));
			responseObj.put("engineCapacity", obj.getString("engineCapacity"));
			responseObj.put("customPapers", obj.getString("customPapers"));
			responseObj.put("exteriorColour", obj.getString("exteriorColour"));
			responseObj.put("interiorColour", obj.getString("interiorColour"));
			responseObj.put("driverType", obj.getString("driverType"));
			responseObj.put("diagnostics", obj.getString("diagnostics"));
			responseObj.put("airbag", obj.getString("airbag"));
			responseObj.put("carPrice", obj.getString("carPrice"));
			responseObj.put("vin", obj.getString("vin"));
			responseObj.put("vehicleId", obj.getString("vehicleId"));
			responseObj.put("model", obj.getString("model"));
			responseObj.put("make", obj.getString("make"));
			responseObj.put("description", obj.getString("description"));
			responseObj.put("noOfDays", obj2.getInt("noOfDays"));
			responseObj.put("accountStatus", obj2.getString("accountStatus"));

			JSONObject obj3 = new JSONObject();
			obj3.put("id", obj.getJSONObject("freeAdCarUpload").getInt("id"));
			obj3.put("carImage", obj.getJSONObject("freeAdCarUpload").getString("carImage"));
			obj3.put("carImage1", obj.getJSONObject("freeAdCarUpload").getString("carImage1"));
			obj3.put("carImage2", obj.getJSONObject("freeAdCarUpload").getString("carImage2"));
			obj3.put("carImage3", obj.getJSONObject("freeAdCarUpload").getString("carImage3"));
			obj3.put("carImage4", obj.getJSONObject("freeAdCarUpload").getString("carImage4"));
			obj3.put("carImage5", obj.getJSONObject("freeAdCarUpload").getString("carImage5"));
			obj3.put("carImage6", obj.getJSONObject("freeAdCarUpload").getString("carImage6"));
			obj3.put("carImage7", obj.getJSONObject("freeAdCarUpload").getString("carImage7"));
			responseObj.put("freeAdCarUpload", obj3);

			response = responseObj.toString();

		} catch (Exception ex) {

			responseObj.put("error", ex.getMessage());
			response = responseObj.toString();

		}

		return response;

	}

	@GetMapping("/sellersendfreeadUploadDetailsForpublshed/{id}")
	public String getFreeAdCarDetailsByIdPublished(@PathVariable("id") Long id,
			@RequestHeader(name = "Authorization") String authHeader) {

		ClientController cc = new ClientController();
		JSONObject responseObj = new JSONObject();
		String response = "";

		try {

			String freeadCardetailsById = ClientController.getFreeAdCaruploadById((Long) id, authHeader);
			System.out.println("freeadCardetailsById: " + freeadCardetailsById);
			JSONObject obj = new JSONObject(freeadCardetailsById);
			String freeAdId = obj.getString("freeAdId");
			System.out.println("freeAdId: " + freeAdId);

			String freeAdDetails = ClientController.getFreeAdById2(Integer.parseInt(freeAdId), authHeader);
			JSONObject obj2 = new JSONObject(freeAdDetails);

			responseObj.put("id", obj.getInt("id"));
			responseObj.put("productTitle", obj.getString("productTitle"));
			responseObj.put("bodyType", obj.getString("bodyType"));
			responseObj.put("engineType", obj.getString("engineType"));
			responseObj.put("fuelType", obj.getString("fuelType"));
			responseObj.put("transmission", obj.getString("transmission"));
			responseObj.put("mileage", obj.getString("mileage"));
			responseObj.put("engineCapacity", obj.getString("engineCapacity"));
			responseObj.put("customPapers", obj.getString("customPapers"));
			responseObj.put("exteriorColour", obj.getString("exteriorColour"));
			responseObj.put("interiorColour", obj.getString("interiorColour"));
			responseObj.put("driverType", obj.getString("driverType"));
			responseObj.put("diagnostics", obj.getString("diagnostics"));
			responseObj.put("airbag", obj.getString("airbag"));
			responseObj.put("carPrice", obj.getString("carPrice"));
			responseObj.put("vin", obj.getString("vin"));
			responseObj.put("vehicleId", obj.getString("vehicleId"));
			responseObj.put("model", obj.getString("model"));
			responseObj.put("make", obj.getString("make"));
			responseObj.put("description", obj.getString("description"));
			responseObj.put("noOfDays", obj2.getInt("noOfDays"));
			responseObj.put("accountStatus", obj2.getString("accountStatus"));

			JSONObject obj3 = new JSONObject();
			obj3.put("id", obj.getJSONObject("freeAdCarUpload").getInt("id"));
			obj3.put("carImage", obj.getJSONObject("freeAdCarUpload").getString("carImage"));
			obj3.put("carImage1", obj.getJSONObject("freeAdCarUpload").getString("carImage1"));
			obj3.put("carImage2", obj.getJSONObject("freeAdCarUpload").getString("carImage2"));
			obj3.put("carImage3", obj.getJSONObject("freeAdCarUpload").getString("carImage3"));
			obj3.put("carImage4", obj.getJSONObject("freeAdCarUpload").getString("carImage4"));
			obj3.put("carImage5", obj.getJSONObject("freeAdCarUpload").getString("carImage5"));
			obj3.put("carImage6", obj.getJSONObject("freeAdCarUpload").getString("carImage6"));
			obj3.put("carImage7", obj.getJSONObject("freeAdCarUpload").getString("carImage7"));
			responseObj.put("freeAdCarUpload", obj3);

			response = responseObj.toString();

		} catch (Exception ex) {

			responseObj.put("error", ex.getMessage());
			response = responseObj.toString();

		}

		return response;

	}

	@GetMapping({ "/approved" })
	public ResponseEntity<List<FreeAdCarDetails>> getAllApprovedFreeAds() {
		List<FreeAdCarDetails> freeAds = this.freeAdCarDetailsService.getAllApprovedFreeAds();
		return new ResponseEntity(freeAds, HttpStatus.OK);
	}

	@GetMapping({ "/unapproved" })
	public ResponseEntity<List<FreeAdCarDetails>> getAllUnapprovedFreeAds() {
		List<FreeAdCarDetails> freeAds = this.freeAdCarDetailsService.getAllUnapprovedFreeAds();
		return new ResponseEntity(freeAds, HttpStatus.OK);
	}

	@GetMapping({ "/rejected" })
	public ResponseEntity<List<FreeAdCarDetails>> getAllRejectedFreeAds() {
		List<FreeAdCarDetails> freeAds = this.freeAdCarDetailsService.getAllRejectedFreeAds();
		return new ResponseEntity(freeAds, HttpStatus.OK);
	}

	@GetMapping({ "/expired" })
	public ResponseEntity<List<FreeAdCarDetails>> getAllCompletedFreeAds() {
		List<FreeAdCarDetails> freeAds = this.freeAdCarDetailsService.getAllCompletedFreeAds();
		return new ResponseEntity(freeAds, HttpStatus.OK);
	}

	@GetMapping({ "/publishASingleFreead/{id}" })
	public FreeAdCarDetails getPublishedFreeAdCarDetails(@PathVariable Long id) {
		FreeAdCarDetails freeAdResponse = freeAdCarDetailsService.publishApprovedFreeadById(id);
		return freeAdResponse;
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/approveorreject/update/{id}/{accountStatus}")
	public ResponseEntity<String> updateAccountStatus(@PathVariable Long id, @PathVariable String accountStatus,
			@RequestHeader(name = "Authorization") String authHeader) {

		System.out.println("START UPDATING FREEAD SUBSCRIPTION..........");

		String freeadCardetailsById = ClientController.getFreeAdCaruploadById((Long) id, authHeader);
		System.out.println("freeadCardetailsById: " + freeadCardetailsById);
		JSONObject obj = new JSONObject(freeadCardetailsById);
		System.out.println("obj: " + obj);

		String freeAdId = obj.getString("freeAdId");
		System.out.println("freeAdId: " + freeAdId);

		ResponseEntity<String> result = new ResponseEntity<String>(
				freeAdRepository.updateAccountStatus(accountStatus, Integer.parseInt(freeAdId)) + " record(s)"
						+ accountStatus + ".",
				HttpStatus.OK);
		System.out.println("freead subscription approval or rejected: " + result);

		ResponseEntity<String> result2 = new ResponseEntity<String>(
				freeAdRepository.updateIsused(Integer.parseInt(freeAdId)) + " record(s).", HttpStatus.OK);
		System.out.println("freead subscription update isUsed: " + result2);

		System.out.println("START UPDATING FREEAD CAR DETAILS SUBSCRIPTION..........");

		ResponseEntity<String> result3 = new ResponseEntity<String>(
				freeAdCarDetailsRepository.updateIsused(id) + " record(s).", HttpStatus.OK);
		System.out.println("freeadCarDetails subscription update isUsed: " + result3);

		return new ResponseEntity<String>(
				freeAdCarDetailsRepository.updateAccountStatus(accountStatus, id) + " record(s).", HttpStatus.OK);
	}

	// *********************************************************END*****************************************************************************

	@RequestMapping(value = "/{freeAdCarDetailsId}", method = RequestMethod.GET)
	@ResponseBody
	public FreeAdCarDetails getFreeAdCarDetails(@PathVariable Long freeAdCarDetailsId) {
		FreeAdCarDetails freeAdCarDetailsResponse = freeAdCarDetailsService
				.findByFreeAdCarDetailsId(freeAdCarDetailsId);
		return freeAdCarDetailsResponse;
	}

	@RequestMapping(value = "/publish/{id}/{token}", method = RequestMethod.GET)
	@ResponseBody
	public String getFreeAdCarDetailsPublished(@PathVariable int id, @PathVariable String token) {
		ClientController cc = new ClientController();
		String id2 = String.valueOf(id);
		String response = cc.getFreeAdCartobePublished(id2, token);
		return response;
	}

	@PutMapping({ "/{id}" })
	public ResponseEntity<FreeAd> updateFreeAd(@PathVariable("id") Long id,
			@RequestBody FreeAdCarDetails freeAdCarDetails) {
		this.freeAdCarDetailsService.updateFreeAdCarDetails(id, freeAdCarDetails);
		return new ResponseEntity(this.freeAdCarDetailsService.findByFreeAdCarDetailsId(id), HttpStatus.OK);
	}

	@DeleteMapping({ "/{freeAdId}" })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<FreeAdCarDetails> deleteFreeAd(@PathVariable("freeAdId") Long freeAdId,@RequestHeader(name = "Authorization") String authHeader) {
		
		long id = FreeAdClientController.getFreeAdSubscriptionIdFromCarUploadDetails(freeAdId, authHeader);
		System.out.println("userCarUploadDetails id:  " + id);
		FreeAdClientController fc = new FreeAdClientController();
		
		String delete = fc.deleteFreeAdCarAndSubscription(id,authHeader);
		System.out.println("delete 1: " + delete);
		this.freeAdCarDetailsService.deleteFreeAdCarDetails(freeAdId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<FreeAdCarDetails> deleteFreeAd2(@PathVariable("freeAdId") Long freeAdId,@RequestHeader(name = "Authorization") String authHeader) {
		
		//long id = FreeAdClientController.getFreeAdSubscriptionIdFromCarUploadDetails(freeAdId, authHeader);
		//System.out.println("userCarUploadDetails id:  " + id);
		//FreeAdClientController fc = new FreeAdClientController();
		
		//String delete = fc.deleteFreeAdCarAndSubscription2(id,authHeader);
		//System.out.println("delete 1: " + delete);
		this.freeAdCarDetailsService.deleteFreeAdCarDetails(freeAdId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
	
	
	@DeleteMapping("/admins/{id}")
    public Map <String, Boolean> deleteCarUpload(
        @PathVariable(value = "id") Long freeAdCarDetailsId) throws NotFoundException {
		FreeAdCarDetails freeAdCarDetails = freeAdCarDetailsRepository.findById(freeAdCarDetailsId)
            .orElseThrow(() -> new NotFoundException());

		freeAdCarDetailsRepository.delete(freeAdCarDetails);
        Map <String, Boolean> response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

	@GetMapping
	public ResponseEntity<List<FreeAdCarDetails>> getAllFreeAdCarDetails() {
		List<FreeAdCarDetails> freeAdCarDetails = this.freeAdCarDetailsService.getFreeAdCarDetails();
		return new ResponseEntity(freeAdCarDetails, HttpStatus.OK);
	}

	@GetMapping({ "/usernamebk" })
	public ResponseEntity<List<FreeAdCarDetails>> getKYCByUsername(@RequestParam String username) {
		return new ResponseEntity(this.freeAdCarDetailsRepository.findByUsername(username), HttpStatus.OK);
	}

	@GetMapping({ "/freeAdId" })
	public ResponseEntity<List<PeakAd2>> getFreeadCaruploadByFreeAdId(@RequestParam String freeAdId) {
		return new ResponseEntity(this.freeAdCarDetailsRepository.findByFreeAdId(freeAdId), HttpStatus.OK);
	}

	@GetMapping({ "/freeadcarbyId/{id}" })
	public FreeAdCarDetails getPublishedFreeAd(@PathVariable Long id) {
		FreeAdCarDetails freeAdCarDetailsResponse = freeAdCarDetailsService.getFreeAdCarDetailsById2(id);
		return freeAdCarDetailsResponse;
	}

}
