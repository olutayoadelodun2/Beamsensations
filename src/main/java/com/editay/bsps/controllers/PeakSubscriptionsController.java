/* Decompiler 22ms, total 786ms, lines 161 */
package com.editay.bsps.controllers;

import com.editay.bsps.models.PeakAd2;
import com.editay.bsps.models.PeakSubscription;
import com.editay.bsps.repository.PeakAd2Repository;
import com.editay.bsps.repository.PeakSubscriptionRepository;
import com.editay.bsps.service.PeakAd2Service;
import com.editay.bsps.service.PeakSubscriptionService;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
   origins = {"*"},
   maxAge = 3600L
)
@RestController
@RequestMapping({"/api/peaksubscriptions"})
public class PeakSubscriptionsController {
   PeakSubscriptionService peakSubscriptionService;
   @Autowired
   PeakSubscriptionRepository peakSubscriptionRepository;
   PeakAd2Service peakAdService;
   @Autowired
   PeakAd2Repository peakAdRepository;

   public PeakSubscriptionsController(PeakSubscriptionService peakSubscriptionService) {
      this.peakSubscriptionService = peakSubscriptionService;
   }

   @GetMapping
   public ResponseEntity<List<PeakSubscription>> getAllPeakSubscriptions() {
      List<PeakSubscription> peakSubscriptions = this.peakSubscriptionService.getPeakSubscriptions();
      return new ResponseEntity(peakSubscriptions, HttpStatus.OK);
   }

   @GetMapping({"/{peakSubscriptionId}"})
   public ResponseEntity<PeakSubscription> getTodo(@PathVariable Long peakSubscriptionId) {
      return new ResponseEntity(this.peakSubscriptionService.getPeakSubscriptionById(peakSubscriptionId), HttpStatus.OK);
   }

   @PostMapping
   public ResponseEntity<PeakSubscription> saveTodo(@RequestBody PeakSubscription peakSubscription) {
      HttpHeaders httpHeaders = new HttpHeaders();
      ClientController pc = new ClientController();
      String peakDetails = ClientController.getPeakDetails(peakSubscription.getPeakid());
      System.out.println("***************************** PEAK AD DETAILS *************************************************");
      System.out.println(peakDetails);
      System.out.println();
      System.out.println();
      System.out.println();
      String peakDetailsConverted = peakDetails.replace("[", "").replace("]", "");
      System.out.println(peakDetailsConverted);
      System.out.println();
      System.out.println();
      System.out.println();
      JSONObject peakDetailsObj = new JSONObject(peakDetailsConverted);
      System.out.println("***************************** DEALERS DETAILS *************************************************");
      String dealerDetails = ClientController.getDealerDetails(peakSubscription.getUsername());
      System.out.println(dealerDetails);
      System.out.println();
      System.out.println();
      System.out.println();
      String dealerDetailsConverted = dealerDetails.replace("[", "").replace("]", "");
      System.out.println(dealerDetailsConverted);
      System.out.println();
      System.out.println();
      System.out.println();
      JSONObject dealerDetailsObj = new JSONObject(dealerDetailsConverted);
      System.out.println();
      System.out.println("***************************** PAYMENT DETAILS *************************************************");
      ResponseEntity result;
      if (peakDetailsObj.getString("peakId").equals(peakSubscription.getPeakid())) {
         if (dealerDetailsObj.getString("username").equals(peakSubscription.getUsername())) {
            peakSubscription.setAmount(String.valueOf(peakDetailsObj.getInt("amount")));
            PeakSubscription ps2 = new PeakSubscription();
            ps2.setAmount(peakSubscription.getAmount());
            ps2.setEmail(dealerDetailsObj.getString("email"));
            System.out.println("amount: " + peakSubscription.getAmount() + " and email: " + peakSubscription.getEmail());
            String paymentDetails = pc.makePaystackPayment(ps2);
            System.out.println("paymentDetails: " + paymentDetails);
            System.out.println();
            System.out.println();
            System.out.println();
            JSONObject paymentDetailsObj = new JSONObject(paymentDetails);
            if (paymentDetailsObj.getString("status").equals("true")) {
               peakSubscription.setAddress(dealerDetailsObj.getString("address"));
               peakSubscription.setCity(dealerDetailsObj.getString("city"));
               peakSubscription.setFirstname(dealerDetailsObj.getString("firstname"));
               peakSubscription.setZipcode(dealerDetailsObj.getString("zipcode"));
               peakSubscription.setEmail(dealerDetailsObj.getString("email"));
               peakSubscription.setMiddlename(dealerDetailsObj.getString("middlename"));
               peakSubscription.setMobile(dealerDetailsObj.getString("mobile"));
               peakSubscription.setPhonenumber(dealerDetailsObj.getString("phonenumber"));
               peakSubscription.setState(dealerDetailsObj.getString("state"));
               peakSubscription.setStatus("PENDING");
               peakSubscription.setSurname(dealerDetailsObj.getString("surname"));
               peakSubscription.setTitle(dealerDetailsObj.getString("title"));
               peakSubscription.setAuthorizationUrl(paymentDetailsObj.getJSONObject("data").getString("authorization_url"));
               peakSubscription.setAccessCode(paymentDetailsObj.getJSONObject("data").getString("access_code"));
               peakSubscription.setReference(paymentDetailsObj.getJSONObject("data").getString("reference"));
               PeakSubscription peakSubscription1 = this.peakSubscriptionService.insert(peakSubscription);
               httpHeaders.add("peakSubscription", "/api/peaksubscriptions/" + peakSubscription1.getId().toString());
               result = new ResponseEntity(peakSubscription1, httpHeaders, HttpStatus.CREATED);
            } else {
               peakSubscription = null;
               result = new ResponseEntity(peakSubscription, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
            }
         } else {
            peakSubscription = null;
            result = new ResponseEntity(peakSubscription, httpHeaders, HttpStatus.NOT_FOUND);
         }
      } else {
         peakSubscription = null;
         result = new ResponseEntity(peakSubscription, httpHeaders, HttpStatus.NOT_FOUND);
      }

      return result;
   }

   @PutMapping({"/{peakSubscriptionId}"})
   public ResponseEntity<PeakSubscription> updatePeakSubscription(@PathVariable("peakSubscriptionId") Long peakSubscriptionId, @RequestBody PeakSubscription peakSubscription) {
      this.peakSubscriptionService.updatePeakSubscriptionStatus(peakSubscriptionId, peakSubscription);
      return new ResponseEntity(this.peakSubscriptionService.getPeakSubscriptionById(peakSubscriptionId), HttpStatus.OK);
   }

   @DeleteMapping({"/{peakSubscriptionId}"})
   public ResponseEntity<PeakSubscription> deletePeakSubscription(@PathVariable("peakSubscriptionId") Long peakSubscriptionId) {
      this.peakSubscriptionService.deletePeakSubscription(peakSubscriptionId);
      return new ResponseEntity(HttpStatus.NO_CONTENT);
   }

   @GetMapping({"/username"})
   public ResponseEntity<List<PeakSubscription>> getPeakSubscriptionByUsername(@RequestParam String username) {
      return new ResponseEntity(this.peakSubscriptionRepository.findByUsername(username), HttpStatus.OK);
   }

   @GetMapping({"/email"})
   public ResponseEntity<List<PeakSubscription>> getPeakSubscriptionByEmail(@RequestParam String email) {
      return new ResponseEntity(this.peakSubscriptionRepository.findByEmail(email), HttpStatus.OK);
   }

   @GetMapping({"/{peakAdId}"})
   public ResponseEntity<PeakAd2> getPeakAd(@PathVariable Long peakAdId) {
      return new ResponseEntity(this.peakAdService.getPeakAdById(peakAdId), HttpStatus.OK);
   }
}