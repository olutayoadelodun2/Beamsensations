package com.editay.bsps.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.editay.bsps.dto.FreeAdUpdateDto;
import com.editay.bsps.models.FreeAd;
import com.editay.bsps.models.FreeAdCarDetails;
import com.editay.bsps.models.FreeAdCarUpload;
import com.editay.bsps.models.KYC;
import com.editay.bsps.repository.FreeAdCarDetailsRepository;
import com.editay.bsps.utility.CommonUtil;


@Service
public class FreeAdCarDetailsServiceImpl implements FreeAdCarDetailsService {

	@Autowired
	private FreeAdCarDetailsRepository freeAdCarDetailsRepository;

	@Override
	public FreeAdCarDetails saveFreeAdCarDetails(FreeAdCarDetails freeAdCarDetails) {
		// TODO Auto-generated method stub

		FreeAdCarUpload freeAdCarUpload = freeAdCarDetails.getFreeAdCarUpload();
		freeAdCarUpload.setFreeAdCarDetails(freeAdCarDetails);
		freeAdCarDetails = freeAdCarDetailsRepository.save(freeAdCarDetails);
		return freeAdCarDetails;
	}

	@Override
	public FreeAdCarDetails findByFreeAdCarDetailsId(Long freeAdCarDetailsId) {
		// TODO Auto-generated method stub
		FreeAdCarDetails freeAdCarDetails = freeAdCarDetailsRepository.findByFreeAdCarDetailsId(freeAdCarDetailsId);
		return freeAdCarDetails;
	}

	@Override
	public void deleteFreeAdCarDetails(Long id) {
		// TODO Auto-generated method stub
		 this.freeAdCarDetailsRepository.deleteById(id);
		
	}

	@Override
	public void updateFreeAdCarDetails(Long id, FreeAdCarDetails freeAdCarDetails) {
		// TODO Auto-generated method stub
		FreeAdCarDetails freeAdCarDetailsFromDb = (FreeAdCarDetails)this.freeAdCarDetailsRepository.findById(id).get();
	      System.out.println(freeAdCarDetailsFromDb.toString());
	      freeAdCarDetailsFromDb.setAirbag(freeAdCarDetails.getAirbag());
	      freeAdCarDetailsFromDb.setCarPrice(freeAdCarDetails.getCarPrice());
	      freeAdCarDetailsFromDb.setCustomPapers(freeAdCarDetails.getCustomPapers());
	      freeAdCarDetailsFromDb.setDescription(freeAdCarDetails.getDescription());
	      freeAdCarDetailsFromDb.setDiagnostics(freeAdCarDetails.getDiagnostics());
	      freeAdCarDetailsFromDb.setDriverType(freeAdCarDetails.getDriverType());
	      freeAdCarDetailsFromDb.setEngineCapacity(freeAdCarDetails.getEngineCapacity());
	      freeAdCarDetailsFromDb.setEngineType(freeAdCarDetails.getEngineType());
	      freeAdCarDetailsFromDb.setExteriorColour(freeAdCarDetails.getExteriorColour());
	      freeAdCarDetailsFromDb.setFreeAdId(freeAdCarDetails.getFreeAdId());
	      freeAdCarDetailsFromDb.setFuelType(freeAdCarDetails.getFuelType());
	      freeAdCarDetailsFromDb.setInteriorColour(freeAdCarDetails.getInteriorColour());
	      freeAdCarDetailsFromDb.setMake(freeAdCarDetails.getMake());
	      freeAdCarDetailsFromDb.setMileage(freeAdCarDetails.getMileage());
	      freeAdCarDetailsFromDb.setModel(freeAdCarDetails.getModel());
	      freeAdCarDetailsFromDb.setTransmission(freeAdCarDetails.getTransmission());
	      freeAdCarDetailsFromDb.setUsername(freeAdCarDetails.getUsername());
	      freeAdCarDetailsFromDb.setVehicleId(freeAdCarDetails.getVehicleId());
	      freeAdCarDetailsFromDb.setVin(freeAdCarDetails.getVin());
	      freeAdCarDetailsFromDb.setAirbag(freeAdCarDetails.getAirbag());
	      
	      this.freeAdCarDetailsRepository.save(freeAdCarDetailsFromDb);
		
	}

	@Override
	public List<FreeAdCarDetails> getFreeAdCarDetails() {
		// TODO Auto-generated method stub
		List<FreeAdCarDetails> freeAdCarDetails = new ArrayList();
	      this.freeAdCarDetailsRepository.findAll().forEach(freeAdCarDetails::add);
	      return freeAdCarDetails;
	}

	@Override
	public FreeAdCarDetails getFreeAdCaruploadByFreeadId(String freeadId) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public FreeAdCarDetails getFreeAdCarDetailsById(Long id) {
		// TODO Auto-generated method stub
		 return this.freeAdCarDetailsRepository.findById(id).get();
	}

	@Override
	public FreeAdCarDetails getFreeAdCarDetailsById2(Long id) {
		// TODO Auto-generated method stub
		FreeAdCarDetails freeAdCarDetails = freeAdCarDetailsRepository.findByFreeAdCarDetailsId(id);
		return freeAdCarDetails;
	}

	@Override
	public List<FreeAdCarDetails> getAllApprovedFreeAds() {
		// TODO Auto-generated method stub
		List<FreeAdCarDetails> freeAds = new ArrayList();
		this.freeAdCarDetailsRepository.getAllApprovedFreead().forEach(freeAds::add);
		return freeAds;
	}

	@Override
	public List<FreeAdCarDetails> getAllUnapprovedFreeAds() {
		// TODO Auto-generated method stub
		List<FreeAdCarDetails> freeAds = new ArrayList();
		this.freeAdCarDetailsRepository.getAllUnapprovedFreead().forEach(freeAds::add);
		return freeAds;
	}

	@Override
	public List<FreeAdCarDetails> getAllRejectedFreeAds() {
		// TODO Auto-generated method stub
		List<FreeAdCarDetails> freeAds = new ArrayList();
		this.freeAdCarDetailsRepository.getAllRejectedFreead().forEach(freeAds::add);
		return freeAds;
	}

	@Override
	public List<FreeAdCarDetails> getAllCompletedFreeAds() {
		// TODO Auto-generated method stub
		List<FreeAdCarDetails> freeAds = new ArrayList();
		this.freeAdCarDetailsRepository.getAllCompletedFreead().forEach(freeAds::add);
		return freeAds;
	}

	@Override
	public FreeAdCarDetails publishApprovedFreeadById(Long id) {
		// TODO Auto-generated method stub
		FreeAdCarDetails freeAd = freeAdCarDetailsRepository.publishApprovedFreeadById(id);
		return freeAd;
	}

	@Override
	public void updateFreeAdAfterPayment(Long id, FreeAdUpdateDto freeAdCarDetails) {
		// TODO Auto-generated method stub
		this.freeAdCarDetailsRepository.updateFreeAdCarDetailsByFreeAdId(freeAdCarDetails.getAccountStatus(), id);
		
	}

	

}
