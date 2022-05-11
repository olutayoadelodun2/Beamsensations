package com.editay.bsps.service;

import com.editay.bsps.models.FreeAd;
import com.editay.bsps.models.FreeAdCarDetails;

import java.util.List;

public interface FreeAdService {
	
   List<FreeAd> getFreeAds();

   FreeAd getFreeAdById(Long id);

   FreeAd getFreeAdByFreeId(String freeId);
   
   FreeAd publishedFreeAd(String freeAdId);

   List<FreeAd> findFreeAdByFreeAdId(String freeAdId);

   FreeAd insert(FreeAd freeAd);

   void updateFreeAd(Long id, FreeAd freeAd);

   void updateFreeAdForAccountStatus(long id, FreeAd freeAd);

   void updateFreeAdAfterPayment(Long id, FreeAd freeAd);

   void deleteFreeAd(Long peakAdId);
   
   List<FreeAd> getAllApprovedFreeAds();
   
   List<FreeAd> getAllUnapprovedFreeAds();
   
   List<FreeAd> getAllRejectedFreeAds();
   
   List<FreeAd> getAllCompletedFreeAds();
   
   public FreeAd publishApprovedFreeadById(int id); 
   
   
}
