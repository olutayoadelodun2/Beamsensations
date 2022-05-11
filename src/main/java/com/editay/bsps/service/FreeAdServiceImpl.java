package com.editay.bsps.service;

import com.editay.bsps.models.FreeAd;
import com.editay.bsps.models.FreeAdCarDetails;
import com.editay.bsps.repository.FreeAdRepository;
import com.editay.bsps.utility.CommonUtil;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FreeAdServiceImpl implements FreeAdService {
	FreeAdRepository freeAdRepository;

	public FreeAdServiceImpl(FreeAdRepository freeAdRepository) {
		this.freeAdRepository = freeAdRepository;
	}

	public List<FreeAd> getFreeAds() {
		List<FreeAd> freeAds = new ArrayList();
		this.freeAdRepository.findAll().forEach(freeAds::add);
		return freeAds;
	}

	public FreeAd getFreeAdById(Long id) {
		return (FreeAd) this.freeAdRepository.findById(id).get();
	}
	
	public FreeAd publishedFreeAd(String freeAdId) {
		return (FreeAd) this.freeAdRepository.findByAccountStatusAndId(freeAdId);
	}

	public FreeAd getFreeAdByFreeId(String freeId) {
		return null;
	}

	public FreeAd insert(FreeAd freeAd) {
		freeAd.setFreeAdId(CommonUtil.freeId(freeAd.getNoOfDays()));
		return (FreeAd) this.freeAdRepository.save(freeAd);
	}

	public void updateFreeAd(Long id, FreeAd freeAd) {
		FreeAd freeAdFromDb = (FreeAd) this.freeAdRepository.findById(id).get();
		System.out.println(freeAdFromDb.toString());
		freeAd.setAccountStatus("UNAPPROVED");
		freeAdFromDb.setAccountStatus(freeAd.getAccountStatus());
		freeAdFromDb.setFreeAdId(CommonUtil.freeId(freeAdFromDb.getNoOfDays()));
		freeAdFromDb.setFreeDescription(freeAd.getFreeDescription());
		freeAdFromDb.setNoOfDays(freeAd.getNoOfDays());
		freeAdFromDb.setSubscriptionDate(freeAd.getSubscriptionDate());
		freeAdFromDb.setFreeDescription(freeAdFromDb.getNoOfDays() + " free ad.");
		freeAdFromDb.setSubscriptionExpireDate(
				CommonUtil.expireDate(freeAd.getSubscriptionDate().toString(), (int) freeAdFromDb.getNoOfDays()));
		this.freeAdRepository.save(freeAdFromDb);
	}

	public void updateFreeAdForAccountStatus(long id, FreeAd freeAd) {
		FreeAd freeAdFromDb = (FreeAd) this.freeAdRepository.findById(id).get();
		System.out.println(freeAdFromDb.toString());
		freeAd.setAccountStatus("UNAPPROVED");
		freeAdFromDb.setAccountStatus(freeAd.getAccountStatus());
		freeAdFromDb.setFreeAdId(CommonUtil.freeId(freeAdFromDb.getNoOfDays()));
		freeAdFromDb.setFreeDescription(freeAd.getFreeDescription());
		freeAdFromDb.setNoOfDays(freeAd.getNoOfDays());
		freeAdFromDb.setSubscriptionDate(freeAd.getSubscriptionDate());
		freeAdFromDb.setFreeDescription(freeAdFromDb.getNoOfDays() + " free ad.");
		freeAdFromDb.setSubscriptionExpireDate(
				CommonUtil.expireDate(freeAd.getSubscriptionDate().toString(), (int) freeAdFromDb.getNoOfDays()));
		this.freeAdRepository.save(freeAdFromDb);
	}

	public void updateFreeAdAfterPayment(Long id, FreeAd freeAd) {
		this.freeAdRepository.updateFreeAdByFreeAdId(freeAd.getAccountStatus(), id);
	}

	public void deleteFreeAd(Long freeAdId) {
		this.freeAdRepository.deleteById(freeAdId);
	}

	public List<FreeAd> findFreeAdByFreeAdId(String freeAdId) {
		return null;
	}

	@Override
	public List<FreeAd> getAllApprovedFreeAds() {
		// TODO Auto-generated method stub
		List<FreeAd> freeAds = new ArrayList();
		this.freeAdRepository.getAllApprovedFreead().forEach(freeAds::add);
		return freeAds;
	}

	@Override
	public List<FreeAd> getAllRejectedFreeAds() {
		// TODO Auto-generated method stub
		List<FreeAd> freeAds = new ArrayList();
		this.freeAdRepository.getAllRejectedFreead().forEach(freeAds::add);
		return freeAds;
	}

	@Override
	public List<FreeAd> getAllUnapprovedFreeAds() {
		// TODO Auto-generated method stub
		List<FreeAd> freeAds = new ArrayList();
		this.freeAdRepository.getAllUnapprovedFreead().forEach(freeAds::add);
		return freeAds;
	}
	
	@Override
	public List<FreeAd> getAllCompletedFreeAds() {
		// TODO Auto-generated method stub
		List<FreeAd> freeAds = new ArrayList();
		this.freeAdRepository.getAllCompletedFreead().forEach(freeAds::add);
		return freeAds;
	}

	@Override
	public FreeAd publishApprovedFreeadById(int id) {
		// TODO Auto-generated method stub
		FreeAd freeAd = freeAdRepository.publishApprovedFreeadById(id);
		return freeAd;
	}
	
	
}
