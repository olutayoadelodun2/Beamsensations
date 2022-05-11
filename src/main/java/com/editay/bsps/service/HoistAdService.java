package com.editay.bsps.service;

import com.editay.bsps.models.HoistAd;
import java.math.BigDecimal;
import java.util.List;

public interface HoistAdService {
   List<HoistAd> getHoistAds();

   HoistAd getHoistAdById(Long id);

   HoistAd getHoistAdByHoistId(String hoistId);

   List<HoistAd> findHoistAdByHoistAdId(String hoistAdId);

   List<HoistAd> findHoistAdByAmount(BigDecimal amount);

   HoistAd insert(HoistAd hoistAd);

   void updateHoistAd(Long id, HoistAd hoistAd);

   void updateHoistAdForAccountStatus(long id, HoistAd hoistAd);

   void updateHoistAdAfterPayment(Long id, HoistAd hoistAd);

   void deleteHoistAd(Long hoistAdId);
}
