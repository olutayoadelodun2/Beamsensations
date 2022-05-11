package com.editay.bsps.service;

import com.editay.bsps.models.PeakAd2;
import com.editay.bsps.repository.PeakAd2Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PeakAd2ServiceImpl implements PeakAd2Service {
   PeakAd2Repository peakAdRepository;

   public PeakAd2ServiceImpl(PeakAd2Repository peakAdRepository) {
      this.peakAdRepository = peakAdRepository;
   }

   public List<PeakAd2> getPeakAds() {
      List<PeakAd2> peakAds = new ArrayList();
      this.peakAdRepository.findAll().forEach(peakAds::add);
      return peakAds;
   }

   public PeakAd2 getPeakAdById(Long id) {
      return (PeakAd2)this.peakAdRepository.findById(id).get();
   }

   public List<PeakAd2> findPeakAdByAmount(BigDecimal amount) {
      return null;
   }

   public PeakAd2 insert(PeakAd2 peakAd2) {
      return (PeakAd2)this.peakAdRepository.save(peakAd2);
   }

   public void updatePeakAd(Long id, PeakAd2 peakAd2) {
      PeakAd2 peakAdFromDb = (PeakAd2)this.peakAdRepository.findById(id).get();
      System.out.println(peakAdFromDb.toString());
      peakAdFromDb.setDays(peakAd2.getDays());
      peakAdFromDb.setAmount(peakAd2.getAmount());
      peakAdFromDb.setPeakDescription(peakAdFromDb.getNoOfPeak() + " peak/" + peakAdFromDb.getDays() + " day (#" + peakAdFromDb.getAmount() + ").");
      this.peakAdRepository.save(peakAdFromDb);
   }

   public void deletePeakAd(Long peakAdId) {
      this.peakAdRepository.deleteById(peakAdId);
   }
}
