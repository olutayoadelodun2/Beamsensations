package com.editay.bsps.repository;

import com.editay.bsps.models.KYC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KYCRepository extends JpaRepository<KYC, Long> {
   List<KYC> findByUsername(String username);
}
