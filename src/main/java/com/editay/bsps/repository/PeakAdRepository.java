package com.editay.bsps.repository;

import com.editay.bsps.models.PeakAd;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PeakAdRepository extends JpaRepository<PeakAd, Long> {
   @Query("SELECT m FROM PeakAd m WHERE m.peakId = ?1")
   List<PeakAd> findByPeakAdId(@Param("peakAdId") String peakAdId);

   List<PeakAd> findByPeakId(String peakId);

   List<PeakAd> findByUsername(String username);

   @Transactional
   @Modifying
   @Query(
      value = "UPDATE peak_ads p set p.account_status = :accountStatus, p.balance =:balance where p.id = :id",
      nativeQuery = true
   )
   void updatePeakAdByPeakId(@Param("balance") String balance, @Param("accountStatus") String accountStatus, @Param("id") long id);
}