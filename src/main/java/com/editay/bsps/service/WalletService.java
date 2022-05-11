/* Decompiler 0ms, total 791ms, lines 20 */
package com.editay.bsps.service;

import com.editay.bsps.models.Wallet;
import java.math.BigDecimal;
import java.util.List;

public interface WalletService {
   List<Wallet> getWallet();

   Wallet getWalletById(Long id);

   void updateWalletById(BigDecimal walletAmount, Long id);

   Wallet insert(Wallet wallet);

   void updateWallet(Long id, Wallet wallet);

   void deleteWallet(Long id);
}