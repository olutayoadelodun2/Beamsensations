package com.editay.bsps.repository;

import com.editay.bsps.models.HoistAd;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface HoistAdRepository extends JpaRepository<HoistAd, Long> {
   List<HoistAd> findByHoistId(String hoistId);

   List<HoistAd> findByUsername(String username);

   @Transactional
   @Modifying
   @Query(
      value = "UPDATE hoist_ads h set h.account_status = :accountStatus, h.balance =:balance where h.id = :id",
      nativeQuery = true
   )
   void updateHoistAdByHoistId(@Param("balance") String balance, @Param("accountStatus") String accountStatus, @Param("id") long id);
}
