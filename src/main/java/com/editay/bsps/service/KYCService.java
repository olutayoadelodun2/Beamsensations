package com.editay.bsps.service;

import com.editay.bsps.models.KYC;
import java.util.List;

public interface KYCService {
	
   List<KYC> getKYCs();

   KYC getKYCById(Long id);

   KYC insert(KYC kyc);

   void updateKYC(Long id, KYC kyc);

   void deleteKYC(Long kycId);
}