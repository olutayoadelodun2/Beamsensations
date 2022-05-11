package com.editay.bsps.service;

import com.editay.bsps.models.PeakAd;
import java.math.BigDecimal;
import java.util.List;

public interface PeakAdService {
   List<PeakAd> getPeakAds();

   PeakAd getPeakAdById(Long id);

   PeakAd getPeakAdByPeakId(String peakId);

   List<PeakAd> findPeakAdByPeakAdId(String peakAdId);

   List<PeakAd> findPeakAdByAmount(BigDecimal amount);

   PeakAd insert(PeakAd peakAd);

   void updatePeakAd(Long id, PeakAd peakAd);

   void updatePeakAdForAccountStatus(long id, PeakAd peakAd);

   void updatePeakAdAfterPayment(Long id, PeakAd peakAd);

   void deletePeakAd(Long peakAdId);
}
