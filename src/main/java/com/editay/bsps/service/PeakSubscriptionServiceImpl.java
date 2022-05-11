package com.editay.bsps.service;

import com.editay.bsps.models.PeakSubscription;
import com.editay.bsps.repository.PeakSubscriptionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PeakSubscriptionServiceImpl implements PeakSubscriptionService {
   PeakSubscriptionRepository peakSubscriptionRepository;

   public PeakSubscriptionServiceImpl(PeakSubscriptionRepository peakSubscriptionRepository) {
      this.peakSubscriptionRepository = peakSubscriptionRepository;
   }

   public List<PeakSubscription> getPeakSubscriptions() {
      List<PeakSubscription> peakSubscriptions = new ArrayList();
      this.peakSubscriptionRepository.findAll().forEach(peakSubscriptions::add);
      return peakSubscriptions;
   }

   public PeakSubscription getPeakSubscriptionById(Long id) {
      return (PeakSubscription)this.peakSubscriptionRepository.findById(id).get();
   }

   public PeakSubscription insert(PeakSubscription peakSubscription) {
      return (PeakSubscription)this.peakSubscriptionRepository.save(peakSubscription);
   }

   public void updatePeakSubscription(Long id, PeakSubscription peakSubscription) {
      PeakSubscription peakSubscriptionFromDb = (PeakSubscription)this.peakSubscriptionRepository.findById(id).get();
      System.out.println(peakSubscriptionFromDb.toString());
      peakSubscriptionFromDb.setAmount(peakSubscription.getAmount());
      peakSubscriptionFromDb.setEmail(peakSubscription.getEmail());
      this.peakSubscriptionRepository.save(peakSubscriptionFromDb);
   }

   public void deletePeakSubscription(Long peakSubscriptionId) {
      this.peakSubscriptionRepository.deleteById(peakSubscriptionId);
   }

   public void updatePeakSubscriptionStatus(Long id, PeakSubscription peakSubscription) {
      PeakSubscription peakSubscriptionFromDb = (PeakSubscription)this.peakSubscriptionRepository.findById(id).get();
      System.out.println(peakSubscriptionFromDb.toString());
      peakSubscriptionFromDb.setAmount(peakSubscription.getAmount());
      peakSubscriptionFromDb.setEmail(peakSubscription.getEmail());
      peakSubscriptionFromDb.setStatus(peakSubscription.getStatus());
      this.peakSubscriptionRepository.save(peakSubscriptionFromDb);
   }
}
