/* Decompiler 41ms, total 844ms, lines 129 */
package com.editay.bsps.controllers;

import com.editay.bsps.dto.PaystackTransactionRequestDto;
import com.editay.bsps.dto.PaystackTransactionResponseDto;
import com.editay.bsps.models.PeakAd;
import com.editay.bsps.models.User;
import com.editay.bsps.repository.PeakAdRepository;
import com.editay.bsps.repository.UserRepository;
import com.editay.bsps.service.PaystackInitializeTransactionService;
import com.editay.bsps.service.PeakAdService;
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
@RequestMapping({"/api/peakAds"})
public class PeakAdsController {
   PeakAdService peakAdService;
   @Autowired
   PeakAdRepository peakAdRepository;
   @Autowired
   UserRepository userRepository;
   @Autowired
   private PaystackInitializeTransactionService initializeTransactionService;

   public PeakAdsController(PeakAdService peakAdService) {
      this.peakAdService = peakAdService;
   }

   @GetMapping
   public ResponseEntity<List<PeakAd>> getAllPeakAds() {
      List<PeakAd> peakAds = this.peakAdService.getPeakAds();
      return new ResponseEntity(peakAds, HttpStatus.OK);
   }

   @GetMapping({"/{peakAdId}"})
   public ResponseEntity<PeakAd> getPeakAd(@PathVariable Long peakAdId) {
      return new ResponseEntity(this.peakAdService.getPeakAdById(peakAdId), HttpStatus.OK);
   }

   @PostMapping({"/peakAd"})
   public ResponseEntity<PeakAd> savePeakAd(@RequestBody PeakAd peakAd) {
      peakAd.setPeakDescription(peakAd.getNoOfPeak() + " peak/" + peakAd.getDays() + " day (#" + peakAd.getAmount() + ").");
      peakAd.setSubscriptionExpireDate(CommonUtil.expireDate(peakAd.getSubscriptionDate().toString(), (int)peakAd.getDays()));
      peakAd.setBalance("0");
      peakAd.setAccountStatus("UNAPPROVED");
      PeakAd peakAd1 = this.peakAdService.insert(peakAd);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add("peakAd", "/api/peakAds" + Long.toString(peakAd1.getId()));
      return new ResponseEntity(peakAd1, httpHeaders, HttpStatus.CREATED);
   }

   @PutMapping({"/{peakAdId}"})
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<PeakAd> updatePeakAd(@PathVariable("peakAdId") Long peakAdId, @RequestBody PeakAd peakAd) {
      this.peakAdService.updatePeakAdAfterPayment(peakAdId, peakAd);
      return new ResponseEntity(this.peakAdService.getPeakAdById(peakAdId), HttpStatus.OK);
   }

   @PutMapping({"/update/{peakAdId}"})
   public ResponseEntity<PeakAd> dealerUpdatePeakAd(@PathVariable("peakAdId") Long peakAdId, @RequestBody PeakAd peakAd) {
      this.peakAdService.updatePeakAd(peakAdId, peakAd);
      return new ResponseEntity(this.peakAdService.getPeakAdById(peakAdId), HttpStatus.OK);
   }

   @PutMapping({"/update/status/{peakAdId}"})
   public ResponseEntity<PeakAd> dealerUpdatePeakAd2(@PathVariable("peakAdId") Long peakAdId, @RequestBody PeakAd peakAd) {
      peakAd.setAccountStatus("APPROVED");
      this.peakAdService.updatePeakAdForAccountStatus(peakAdId, peakAd);
      return new ResponseEntity(this.peakAdService.getPeakAdById(peakAdId), HttpStatus.OK);
   }

   @DeleteMapping({"/{peakAdId}"})
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<PeakAd> deletePeakAd(@PathVariable("peakAdId") Long peakAdId) {
      this.peakAdService.deletePeakAd(peakAdId);
      return new ResponseEntity(HttpStatus.NO_CONTENT);
   }

   @GetMapping({"/peakId"})
   public ResponseEntity<List<PeakAd>> getPeakIdByName(@RequestParam String peakId) {
      return new ResponseEntity(this.peakAdRepository.findByPeakId(peakId), HttpStatus.OK);
   }

   @GetMapping({"/user"})
   public ResponseEntity<Optional<User>> getByUsername(@RequestParam String username) {
      return new ResponseEntity(this.userRepository.findByUsername(username), HttpStatus.OK);
   }

   @GetMapping({"/username"})
   public ResponseEntity<List<PeakAd>> getPeakByUsername(@RequestParam String username) {
      return new ResponseEntity(this.peakAdRepository.findByUsername(username), HttpStatus.OK);
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