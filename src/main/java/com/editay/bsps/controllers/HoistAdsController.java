/* Decompiler 9ms, total 818ms, lines 128 */
package com.editay.bsps.controllers;

import com.editay.bsps.dto.PaystackTransactionRequestDto;
import com.editay.bsps.dto.PaystackTransactionResponseDto;
import com.editay.bsps.models.HoistAd;
import com.editay.bsps.models.User;
import com.editay.bsps.repository.HoistAdRepository;
import com.editay.bsps.repository.UserRepository;
import com.editay.bsps.service.HoistAdService;
import com.editay.bsps.service.PaystackInitializeTransactionService;
import com.editay.bsps.utility.CommonUtil;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
   origins = {"*"},
   maxAge = 3600L
)
@RestController
@RequestMapping({"/api/hoistAds"})
public class HoistAdsController {
   HoistAdService hoistAdService;
   @Autowired
   HoistAdRepository hoistAdRepository;
   @Autowired
   UserRepository userRepository;
   @Autowired
   private PaystackInitializeTransactionService initializeTransactionService;

   public HoistAdsController(HoistAdService hoistAdService) {
      this.hoistAdService = hoistAdService;
   }

   @GetMapping
   public ResponseEntity<List<HoistAd>> getAllHoistAds() {
      List<HoistAd> hoistAds = this.hoistAdService.getHoistAds();
      return new ResponseEntity(hoistAds, HttpStatus.OK);
   }

   @GetMapping({"/{hoistAdId}"})
   public ResponseEntity<HoistAd> getHoistAd(@PathVariable Long hoistAdId) {
      return new ResponseEntity(this.hoistAdService.getHoistAdById(hoistAdId), HttpStatus.OK);
   }

   @PostMapping({"/hoistAd"})
   public ResponseEntity<HoistAd> saveHoistAd(@RequestBody HoistAd hoistAd) {
      hoistAd.setHoistDescription(hoistAd.getNoOfMonths() + " months/" + hoistAd.getHoistType() + "(#" + hoistAd.getAmount() + ").");
      hoistAd.setSubscriptionExpireDate(CommonUtil.expireDate(hoistAd.getSubscriptionDate().toString(), (int)hoistAd.getNoOfMonths()));
      hoistAd.setBalance("0");
      hoistAd.setAccountStatus("UNAPPROVED");
      HoistAd hoistAd1 = this.hoistAdService.insert(hoistAd);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add("peakAd", "/api/hoistAds" + Long.toString(hoistAd1.getId()));
      return new ResponseEntity(hoistAd1, httpHeaders, HttpStatus.CREATED);
   }

   @PutMapping({"/{hoistAdId}"})
   public ResponseEntity<HoistAd> updateHoistAd(@PathVariable("hoistAdId") Long hoistAdId, @RequestBody HoistAd hoistAd) {
      this.hoistAdService.updateHoistAdAfterPayment(hoistAdId, hoistAd);
      return new ResponseEntity(this.hoistAdService.getHoistAdById(hoistAdId), HttpStatus.OK);
   }

   @PutMapping({"/update/{hoistAdId}"})
   public ResponseEntity<HoistAd> dealerUpdateHoistAd(@PathVariable("hoistAdId") Long hoistAdId, @RequestBody HoistAd hoistAd) {
      this.hoistAdService.updateHoistAd(hoistAdId, hoistAd);
      return new ResponseEntity(this.hoistAdService.getHoistAdById(hoistAdId), HttpStatus.OK);
   }

   @PutMapping({"/update/status/{hoistAdId}"})
   public ResponseEntity<HoistAd> dealerUpdateHoistAd2(@PathVariable("hoistAdId") Long hoistAdId, @RequestBody HoistAd hoistAd) {
      hoistAd.setAccountStatus("APPROVED");
      this.hoistAdService.updateHoistAdForAccountStatus(hoistAdId, hoistAd);
      return new ResponseEntity(this.hoistAdService.getHoistAdById(hoistAdId), HttpStatus.OK);
   }

   @DeleteMapping({"/{hoistAdId}"})
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<HoistAd> deleteHoistAd(@PathVariable("hoistAdId") Long hoistAdId) {
      this.hoistAdService.deleteHoistAd(hoistAdId);
      return new ResponseEntity(HttpStatus.NO_CONTENT);
   }

   @GetMapping({"/hoistId"})
   public ResponseEntity<List<HoistAd>> getHoistIdByName(@RequestParam String hoistId) {
      return new ResponseEntity(this.hoistAdRepository.findByHoistId(hoistId), HttpStatus.OK);
   }

   @GetMapping({"/user"})
   public ResponseEntity<Optional<User>> getByUsername(@RequestParam String username) {
      return new ResponseEntity(this.userRepository.findByUsername(username), HttpStatus.OK);
   }

   @GetMapping({"/username"})
   public ResponseEntity<List<HoistAd>> getPeakByUsername(@RequestParam String username) {
      return new ResponseEntity(this.hoistAdRepository.findByUsername(username), HttpStatus.OK);
   }

   @GetMapping({"/email"})
   public ResponseEntity<List<User>> getPeakByEmail(@RequestParam String email) {
      return new ResponseEntity(this.userRepository.findByEmail(email), HttpStatus.OK);
   }

   @RequestMapping(
      path = {"/initializetransaction"},
      method = {RequestMethod.POST}
   )
   public PaystackTransactionResponseDto initializeTransaction(@RequestBody PaystackTransactionRequestDto initializeTransactionRequestDTO) {
      PaystackTransactionResponseDto initializeTransaction = this.initializeTransactionService.initializeTransaction(initializeTransactionRequestDTO);
      return initializeTransaction;
   }
}