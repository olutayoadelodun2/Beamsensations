/* Decompiler 5ms, total 776ms, lines 78 */
package com.editay.bsps.controllers;

import com.editay.bsps.models.PeakAd2;
import com.editay.bsps.repository.PeakAd2Repository;
import com.editay.bsps.service.PeakAd2Service;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
   origins = {"*"},
   maxAge = 3600L
)
@RestController
@RequestMapping({"/api/peakAds2"})
public class PeakAds2Controller {
   PeakAd2Service peakAdService;
   @Autowired
   PeakAd2Repository peakAdRepository;

   public PeakAds2Controller(PeakAd2Service peakAdService) {
      this.peakAdService = peakAdService;
   }

   @GetMapping
   public ResponseEntity<List<PeakAd2>> getAllPeakAds() {
      List<PeakAd2> peakAds = this.peakAdService.getPeakAds();
      return new ResponseEntity(peakAds, HttpStatus.OK);
   }

   @GetMapping({"/{id}"})
   public ResponseEntity<PeakAd2> getPeakAd(@PathVariable("id") long id) {
      Optional<PeakAd2> peakAdData = this.peakAdRepository.findById(id);
      return peakAdData.isPresent() ? new ResponseEntity(peakAdData.get(), HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
   }

   @PostMapping({"/peakAd"})
   public ResponseEntity<PeakAd2> savePeakAd(@RequestBody PeakAd2 peakAd) {
      peakAd.setPeakDescription(peakAd.getNoOfPeak() + " peak/" + peakAd.getDays() + " day (#" + peakAd.getAmount() + ").");
      PeakAd2 peakAd1 = this.peakAdService.insert(peakAd);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add("peakAd", "/api/peakAds2" + Long.toString(peakAd1.getId()));
      return new ResponseEntity(peakAd1, httpHeaders, HttpStatus.CREATED);
   }

   @PutMapping({"/update/{peakAdId}"})
   public ResponseEntity<PeakAd2> UpdatePeakAd2(@PathVariable("peakAdId") Long peakAdId, @RequestBody PeakAd2 peakAd) {
      this.peakAdService.updatePeakAd(peakAdId, peakAd);
      return new ResponseEntity(this.peakAdService.getPeakAdById(peakAdId), HttpStatus.OK);
   }

   @DeleteMapping({"/{peakAdId}"})
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<PeakAd2> deletePeakAd(@PathVariable("peakAdId") Long peakAdId) {
      this.peakAdService.deletePeakAd(peakAdId);
      return new ResponseEntity(HttpStatus.NO_CONTENT);
   }

   @GetMapping({"/peakDescription"})
   public ResponseEntity<List<PeakAd2>> getPeakByPeakSubscription(@RequestParam String peakDescription) {
      return new ResponseEntity(this.peakAdRepository.findByPeakDescription(peakDescription), HttpStatus.OK);
   }
}