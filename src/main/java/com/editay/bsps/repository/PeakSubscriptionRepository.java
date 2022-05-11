package com.editay.bsps.repository;

import com.editay.bsps.models.PeakSubscription;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeakSubscriptionRepository extends JpaRepository<PeakSubscription, Long> {
   PeakSubscription getPeakSubscriptionByUsername(String username);

   PeakSubscription getPeakSubscriptionByEmail(String email);

   List<PeakSubscription> findByUsername(String username);

   List<PeakSubscription> findByEmail(String email);
}
