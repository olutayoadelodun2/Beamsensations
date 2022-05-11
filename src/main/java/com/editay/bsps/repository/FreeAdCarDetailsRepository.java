package com.editay.bsps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.editay.bsps.models.FreeAd;
import com.editay.bsps.models.FreeAdCarDetails;

@Repository
public interface FreeAdCarDetailsRepository extends JpaRepository<FreeAdCarDetails, Long> {

	@Query(value = "SELECT * from freead_car_details where id = ?1", nativeQuery = true)
	public FreeAdCarDetails findByFreeAdCarDetailsId(Long FreeAdCarDetailsId);

	List<FreeAdCarDetails> findByUsername(String username);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM free_ads WHERE id = :id", nativeQuery = true) 
	Long deleteByIdNative(@Param("id") Long id);

	/*
	 * @Query(value =
	 * "SELECT * from freead_car_details f where f.free_ad_id = :free_ad_id",
	 * nativeQuery = true) // using @query public List<FreeAdCarDetails>
	 * getFreeadCaruploadById(String freeadId);
	 */

	List<FreeAdCarDetails> findByFreeAdId(String freeadId);

	@Transactional
	@Modifying
	@Query(value = "UPDATE freead_car_details f set f.account_status = :accountStatus  where f.id = :id", nativeQuery = true)
	void updateFreeAdCarDetailsByFreeAdId(@Param("accountStatus") String accountStatus, @Param("id") long id);

	@Query(value = "SELECT * from freead_car_details f where f.account_status = 'APPROVED' and f.id = :id", nativeQuery = true) // using
																																// @query
	public FreeAdCarDetails publishApprovedFreeadById(Long id);

	@Query(value = "SELECT * from freead_car_details where account_status = 'APPROVED'", nativeQuery = true) // using
																												// @query
	List<FreeAdCarDetails> getAllApprovedFreead();

	@Query(value = "SELECT * from freead_car_details where account_status = 'UNAPPROVED'", nativeQuery = true) // using
																												// @query
	List<FreeAdCarDetails> getAllUnapprovedFreead();

	@Query(value = "SELECT * from freead_car_details where account_status = 'REJECTED'", nativeQuery = true) // using
																												// @query
	List<FreeAdCarDetails> getAllRejectedFreead();

	@Query(value = "SELECT * from freead_car_details where account_status = 'COMPLETED'", nativeQuery = true) // using
																												// @query
	List<FreeAdCarDetails> getAllCompletedFreead();

	@Query(value = "SELECT * from freead_car_details where account_status = 'APPROVED' and  freead_id = ?1", nativeQuery = true) // using
																																	// @query
	List<FreeAdCarDetails> findByAccountStatusAndId(String freeAdId);

	@Query(value = "SELECT * from freead_car_details where freead_id = ?1", nativeQuery = true) // using @query
	List<FreeAdCarDetails> findIfApproved(String freeAdId);

	@Query(value = "SELECT * from freead_car_details where account_status = 'UNAPPROVED' and  freead_id = ?1", nativeQuery = true) // using
																																	// @query
	List<FreeAdCarDetails> findByAccountStatusAndIdWhenNotApproved(String freeAdId);

	@Query(value = "SELECT * from freead_car_details where account_status = 'REJECTED' and  freead_id = ?1", nativeQuery = true) // using
																																	// @query
	List<FreeAdCarDetails> findByAccountStatusAndIdWhenRejected(String freeAdId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE freead_car_details c SET c.account_status = :accountStatus WHERE c.id = :id", nativeQuery = true)
	Integer updateAccountStatus(String accountStatus, Long id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE freead_car_details c SET c.is_used = 'Y'  WHERE c.id = :id", nativeQuery = true)
	Integer updateIsused(Long id);

	@Query(value = "SELECT * from freead_car_details where id = ?1", nativeQuery = true) // using @query
	List<FreeAdCarDetails> checkIsusedFlag();

}
