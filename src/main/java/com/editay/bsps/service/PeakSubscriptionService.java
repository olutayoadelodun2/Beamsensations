package com.editay.bsps.service;

import com.editay.bsps.models.PeakSubscription;
import java.util.List;

public interface PeakSubscriptionService {
   List<PeakSubscription> getPeakSubscriptions();

   PeakSubscription getPeakSubscriptionById(Long id);

   PeakSubscription insert(PeakSubscription peakSubscription);

   void updatePeakSubscription(Long id, PeakSubscription peakSubscription);

   void updatePeakSubscriptionStatus(Long id, PeakSubscription peakSubscription);

   void deletePeakSubscription(Long peakSubscriptionId);
}
