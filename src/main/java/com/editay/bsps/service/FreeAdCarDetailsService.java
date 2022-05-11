package com.editay.bsps.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.editay.bsps.dto.FreeAdUpdateDto;
import com.editay.bsps.models.FreeAd;
import com.editay.bsps.models.FreeAdCarDetails;

@Component
public interface FreeAdCarDetailsService {

	public FreeAdCarDetails saveFreeAdCarDetails(FreeAdCarDetails freeAdCarDetails);

	public FreeAdCarDetails findByFreeAdCarDetailsId(Long id);

	void deleteFreeAdCarDetails(Long id);

	void updateFreeAdCarDetails(Long id, FreeAdCarDetails freeAdCarDetails);

	List<FreeAdCarDetails> getFreeAdCarDetails();

	public FreeAdCarDetails getFreeAdCaruploadByFreeadId(String freeadId);

	FreeAdCarDetails getFreeAdCarDetailsById(Long id);

	FreeAdCarDetails getFreeAdCarDetailsById2(Long id);

	List<FreeAdCarDetails> getAllApprovedFreeAds();

	List<FreeAdCarDetails> getAllUnapprovedFreeAds();

	List<FreeAdCarDetails> getAllRejectedFreeAds();

	List<FreeAdCarDetails> getAllCompletedFreeAds();

	public FreeAdCarDetails publishApprovedFreeadById(Long id);
	
	 void updateFreeAdAfterPayment(Long id, FreeAdUpdateDto freeAdUpdateDto);

}
