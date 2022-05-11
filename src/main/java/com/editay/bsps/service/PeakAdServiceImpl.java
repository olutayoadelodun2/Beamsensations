package com.editay.bsps.service;

import com.editay.bsps.models.PeakAd;
import com.editay.bsps.repository.PeakAdRepository;
import com.editay.bsps.utility.CommonUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PeakAdServiceImpl implements PeakAdService {
   PeakAdRepository peakAdRepository;

   public PeakAdServiceImpl(PeakAdRepository peakAdRepository) {
      this.peakAdRepository = peakAdRepository;
   }

   public List<PeakAd> getPeakAds() {
      List<PeakAd> peakAds = new ArrayList();
      this.peakAdRepository.findAll().forEach(peakAds::add);
      return peakAds;
   }

   public PeakAd getPeakAdById(Long id) {
      return (PeakAd)this.peakAdRepository.findById(id).get();
   }

   public PeakAd insert(PeakAd peakAd) {
      peakAd.setPeakId(CommonUtil.peakId(peakAd.getDays(), peakAd.getNoOfPeak()));
      return (PeakAd)this.peakAdRepository.save(peakAd);
   }

   public void updatePeakAd(Long id, PeakAd peakAd) {
      PeakAd peakAdFromDb = (PeakAd)this.peakAdRepository.findById(id).get();
      System.out.println(peakAdFromDb.toString());
      peakAd.setAccountStatus("UNAPPROVED");
      peakAdFromDb.setAccountStatus(peakAd.getAccountStatus());
      peakAdFromDb.setPeakId(CommonUtil.peakId(peakAdFromDb.getDays(), peakAdFromDb.getNoOfPeak()));
      peakAdFromDb.setPeakDescription(peakAd.getPeakDescription());
      peakAdFromDb.setDays(peakAd.getDays());
      peakAdFromDb.setAmount(peakAd.getAmount());
      peakAdFromDb.setSubscriptionDate(peakAd.getSubscriptionDate());
      peakAdFromDb.setPeakDescription(peakAdFromDb.getNoOfPeak() + " peak/" + peakAdFromDb.getDays() + " day (#" + peakAdFromDb.getAmount() + ").");
      peakAdFromDb.setSubscriptionExpireDate(CommonUtil.expireDate(peakAd.getSubscriptionDate().toString(), (int)peakAdFromDb.getDays()));
      this.peakAdRepository.save(peakAdFromDb);
   }

   public void updatePeakAdAfterPayment(Long id, PeakAd peakAd) {
      PeakAd peakAdFromDb = (PeakAd)this.peakAdRepository.findById(id).get();
      System.out.println(peakAdFromDb.toString());
      peakAd.setAccountStatus("APPROVED");
      peakAdFromDb.setAccountStatus(peakAd.getAccountStatus());
      peakAdFromDb.setPeakId(CommonUtil.peakId(peakAdFromDb.getDays(), peakAdFromDb.getNoOfPeak()));
      peakAdFromDb.setPeakDescription(peakAd.getPeakDescription());
      peakAdFromDb.setDays(peakAd.getDays());
      peakAdFromDb.setAmount(peakAd.getAmount());
      peakAdFromDb.setSubscriptionDate(peakAd.getSubscriptionDate());
      peakAdFromDb.setPeakDescription(peakAdFromDb.getNoOfPeak() + " peak/" + peakAdFromDb.getDays() + " day (#" + peakAdFromDb.getAmount() + ").");
      peakAdFromDb.setSubscriptionExpireDate(CommonUtil.expireDate(peakAd.getSubscriptionDate().toString(), (int)peakAdFromDb.getDays()));
      this.peakAdRepository.save(peakAdFromDb);
   }

   public void deletePeakAd(Long peakAdId) {
      this.peakAdRepository.deleteById(peakAdId);
   }

   public PeakAd getPeakAdByPeakId(String peakId) {
      return null;
   }

   public List<PeakAd> findPeakAdByPeakAdId(String peakAdId) {
      return this.peakAdRepository.findByPeakAdId(peakAdId);
   }

   public List<PeakAd> findPeakAdByAmount(BigDecimal amount) {
      return null;
   }

   public void updatePeakAdForAccountStatus(long id, PeakAd peakAd) {
      this.peakAdRepository.updatePeakAdByPeakId(peakAd.getBalance(), peakAd.getAccountStatus(), id);
   }
}
