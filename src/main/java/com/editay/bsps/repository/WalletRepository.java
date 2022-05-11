package com.editay.bsps.repository;

import com.editay.bsps.models.Wallet;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
   List<Wallet> findByUsername(String username);

   Boolean existsByUsername(String username);

   @Transactional
   @Modifying
   @Query(
      value = "UPDATE wallet w set w.wallet_amount = :walletAmount where p.id = :id",
      nativeQuery = true
   )
   void updateWalletById(@Param("walletAmount") BigDecimal walletAmount, @Param("id") long id);
}
