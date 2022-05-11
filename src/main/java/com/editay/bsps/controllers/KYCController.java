/* Decompiler 5ms, total 773ms, lines 83 */
package com.editay.bsps.controllers;

import com.editay.bsps.models.KYC;
import com.editay.bsps.repository.KYCRepository;
import com.editay.bsps.repository.UserRepository;
import com.editay.bsps.service.KYCService;
import java.util.List;
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
@RequestMapping({"/api/kyc"})
public class KYCController {
   KYCService kycService;
   @Autowired
   KYCRepository kycRepository;
   @Autowired
   UserRepository userRepository;

   public KYCController(KYCService kycService) {
      this.kycService = kycService;
   }

   @GetMapping
   public ResponseEntity<List<KYC>> getAllKYCs() {
      List<KYC> kycs = this.kycService.getKYCs();
      return new ResponseEntity(kycs, HttpStatus.OK);
   }

   @GetMapping({"/{kycId}"})
   public ResponseEntity<KYC> getKYC(@PathVariable Long kycId) {
      return new ResponseEntity(this.kycService.getKYCById(kycId), HttpStatus.OK);
   }

   @PostMapping({"/add"})
   public ResponseEntity<KYC> saveKYC(@RequestBody KYC kyc) {
      HttpHeaders httpHeaders = new HttpHeaders();
      if (!this.userRepository.existsByUsername(kyc.getUsername())) {
         return new ResponseEntity((Object)null, httpHeaders, HttpStatus.BAD_REQUEST);
      } else {
         System.out.println("kyc.getCacDocument(): " + kyc.getCacDocument());
         System.out.println("kyc.getNin(): " + kyc.getNin());
         System.out.println("kyc.getPassport(): " + kyc.getPassport());
         KYC kyc2 = this.kycService.insert(kyc);
         httpHeaders.add("kyc", "/api/kyc" + Long.toString(kyc2.getId()));
         return new ResponseEntity(kyc2, httpHeaders, HttpStatus.CREATED);
      }
   }

   @PutMapping({"/{kycId}"})
   public ResponseEntity<KYC> updateKYC(@PathVariable("kycId") Long kycId, @RequestBody KYC kyc) {
      this.kycService.updateKYC(kycId, kyc);
      return new ResponseEntity(this.kycService.getKYCById(kycId), HttpStatus.OK);
   }

   @DeleteMapping({"/{kycId}"})
   public ResponseEntity<KYC> deleteKYC(@PathVariable("kycId") Long kycId) {
      this.kycService.deleteKYC(kycId);
      return new ResponseEntity(HttpStatus.NO_CONTENT);
   }

   @GetMapping({"/username"})
   public ResponseEntity<List<KYC>> getKYCByUsername(@RequestParam String username) {
      return new ResponseEntity(this.kycRepository.findByUsername(username), HttpStatus.OK);
   }
}