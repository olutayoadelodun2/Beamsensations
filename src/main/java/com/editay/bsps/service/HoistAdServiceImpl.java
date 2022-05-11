package com.editay.bsps.service;

import com.editay.bsps.models.HoistAd;
import com.editay.bsps.repository.HoistAdRepository;
import com.editay.bsps.utility.CommonUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HoistAdServiceImpl implements HoistAdService {
   @Autowired
   private HoistAdRepository hoistAdRepository;

   public HoistAdServiceImpl(HoistAdRepository hoistAdRepository) {
      this.hoistAdRepository = hoistAdRepository;
   }

   public List<HoistAd> getHoistAds() {
      List<HoistAd> hoistAds = new ArrayList();
      this.hoistAdRepository.findAll().forEach(hoistAds::add);
      return hoistAds;
   }

   public HoistAd getHoistAdById(Long id) {
      return (HoistAd)this.hoistAdRepository.findById(id).get();
   }

   public HoistAd getHoistAdByHoistId(String hoistId) {
      return null;
   }

   public List<HoistAd> findHoistAdByHoistAdId(String hoistAdId) {
      return this.hoistAdRepository.findByHoistId(hoistAdId);
   }

   public List<HoistAd> findHoistAdByAmount(BigDecimal amount) {
      return null;
   }

   public HoistAd insert(HoistAd hoistAd) {
      hoistAd.setHoistId(CommonUtil.hoistId(hoistAd.getNoOfMonths(), hoistAd.getHoistType()));
      return (HoistAd)this.hoistAdRepository.save(hoistAd);
   }

   public void updateHoistAd(Long id, HoistAd hoistAd) {
      HoistAd hoistAdFromDb = (HoistAd)this.hoistAdRepository.findById(id).get();
      System.out.println(hoistAdFromDb.toString());
      hoistAd.setAccountStatus("UNAPPROVED");
      hoistAdFromDb.setAccountStatus(hoistAd.getAccountStatus());
      hoistAdFromDb.setHoistId(CommonUtil.hoistId(hoistAdFromDb.getNoOfMonths(), hoistAdFromDb.getHoistType()));
      hoistAdFromDb.setHoistDescription(hoistAd.getHoistDescription());
      hoistAdFromDb.setNoOfMonths(hoistAd.getNoOfMonths());
      hoistAdFromDb.setHoistType(hoistAd.getHoistType());
      hoistAdFromDb.setAmount(hoistAd.getAmount());
      hoistAdFromDb.setSubscriptionDate(hoistAd.getSubscriptionDate());
      hoistAdFromDb.setHoistDescription(hoistAdFromDb.getNoOfMonths() + " months/" + hoistAdFromDb.getHoistType() + "(#" + hoistAdFromDb.getAmount() + ").");
      hoistAdFromDb.setSubscriptionExpireDate(CommonUtil.expireDate(hoistAd.getSubscriptionDate().toString(), (int)hoistAdFromDb.getNoOfMonths()));
      this.hoistAdRepository.save(hoistAdFromDb);
   }

   public void updateHoistAdForAccountStatus(long id, HoistAd hoistAd) {
      this.hoistAdRepository.updateHoistAdByHoistId(hoistAd.getBalance(), hoistAd.getAccountStatus(), id);
   }

   public void updateHoistAdAfterPayment(Long id, HoistAd hoistAd) {
   }

   public void deleteHoistAd(Long hoistAdId) {
      this.hoistAdRepository.deleteById(hoistAdId);
   }
}
