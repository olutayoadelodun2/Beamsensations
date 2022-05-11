package com.editay.bsps.service;

import com.editay.bsps.models.PeakAd2;
import java.math.BigDecimal;
import java.util.List;

public interface PeakAd2Service {
   List<PeakAd2> getPeakAds();

   PeakAd2 getPeakAdById(Long id);

   List<PeakAd2> findPeakAdByAmount(BigDecimal amount);

   PeakAd2 insert(PeakAd2 peakAd2);

   void updatePeakAd(Long id, PeakAd2 peakAd2);

   void deletePeakAd(Long peakAdId);
}