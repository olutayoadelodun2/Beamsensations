package com.editay.bsps.repository;

import com.editay.bsps.models.FreeAd;
import com.editay.bsps.models.FreeAdCarDetails;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FreeAdRepository extends JpaRepository<FreeAd, Long> {
	
   List<FreeAd> findByUsername(String username);

   @Transactional
   @Modifying
   @Query(
      value = "UPDATE free_ads f set f.account_status = :accountStatus  where f.id = :id",
      nativeQuery = true
   )
   void updateFreeAdByFreeAdId(@Param("accountStatus") String accountStatus, @Param("id") long id);
   
   @Query(value = "SELECT * from free_ads f where f.account_status = 'APPROVED' and f.id = :id", nativeQuery = true)       // using @query
   public FreeAd publishApprovedFreeadById(int id);
   
   @Query(value = "SELECT * from free_ads where account_status = 'APPROVED'", nativeQuery = true)       // using @query
   List<FreeAd> getAllApprovedFreead();
   
   @Query(value = "SELECT * from free_ads where account_status = 'UNAPPROVED'", nativeQuery = true)       // using @query
   List<FreeAd> getAllUnapprovedFreead();
   
   @Query(value = "SELECT * from free_ads where account_status = 'REJECTED'", nativeQuery = true)       // using @query
   List<FreeAd> getAllRejectedFreead();
   
   @Query(value = "SELECT * from free_ads where account_status = 'COMPLETED'", nativeQuery = true)       // using @query
   List<FreeAd> getAllCompletedFreead();
   
   
   @Query(value = "SELECT * from free_ads where account_status = 'APPROVED' and  freead_id = ?1", nativeQuery = true)       // using @query
   List<FreeAd> findByAccountStatusAndId(String freeAdId);
   
   @Query(value = "SELECT * from free_ads where freead_id = ?1", nativeQuery = true)       // using @query
   List<FreeAd> findIfApproved(String freeAdId);
   
   @Query(value = "SELECT * from free_ads where account_status = 'UNAPPROVED' and  freead_id = ?1", nativeQuery = true)       // using @query
   List<FreeAd> findByAccountStatusAndIdWhenNotApproved(String freeAdId);
   
   @Query(value = "SELECT * from free_ads where account_status = 'REJECTED' and  freead_id = ?1", nativeQuery = true)       // using @query
   List<FreeAd> findByAccountStatusAndIdWhenRejected(String freeAdId);
   
   @Modifying
   @Transactional
   @Query(value = "UPDATE free_ads c SET c.account_status = :accountStatus WHERE c.id = :id", nativeQuery = true)
   Integer updateAccountStatus(String accountStatus, int id);
   
   
   @Modifying
   @Transactional
   @Query(value = "UPDATE free_ads c SET c.is_used = 'Y'  WHERE c.id = :id", nativeQuery = true)
   Integer updateIsused( int id);
   
   
   @Query(value = "SELECT * from free_ads where id = ?1", nativeQuery = true)       // using @query
   List<FreeAd> checkIsusedFlag();
   
   
  
   
}
