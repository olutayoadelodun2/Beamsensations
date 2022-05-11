package com.editay.bsps.service;

import com.editay.bsps.models.KYC;
import com.editay.bsps.repository.KYCRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class KYCServiceImpl implements KYCService {
   KYCRepository kycRepository;

   public KYCServiceImpl(KYCRepository kycRepository) {
      this.kycRepository = kycRepository;
   }

   @Cacheable({"kyc-all"})
   public List<KYC> getKYCs() {
      List<KYC> kycs = new ArrayList();
      this.kycRepository.findAll().forEach(kycs::add);
      return kycs;
   }

   public KYC getKYCById(Long id) {
      return (KYC)this.kycRepository.findById(id).get();
   }

   @Cacheable({"kyc-insert"})
   public KYC insert(KYC kyc) {
      return (KYC)this.kycRepository.save(kyc);
   }

   @Cacheable({"kyc-update"})
   public void updateKYC(Long id, KYC kyc) {
      KYC kycFromDb = (KYC)this.kycRepository.findById(id).get();
      System.out.println(kycFromDb.toString());
      kycFromDb.setAccountNumber(kyc.getAccountNumber());
      kycFromDb.setBankName(kyc.getBankName());
      kycFromDb.setBrand(kyc.getBrand());
      kycFromDb.setCacDocument(kyc.getCacDocument());
      kycFromDb.setNin(kyc.getNin());
      kycFromDb.setPassport(kyc.getPassport());
      kycFromDb.setUsername(kyc.getUsername());
      this.kycRepository.save(kycFromDb);
   }

   @Cacheable({"kyc-delete"})
   public void deleteKYC(Long kycId) {
      this.kycRepository.deleteById(kycId);
   }
}
