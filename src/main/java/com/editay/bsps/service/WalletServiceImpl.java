/* Decompiler 6ms, total 196ms, lines 52 */
package com.editay.bsps.service;

import com.editay.bsps.models.Wallet;
import com.editay.bsps.repository.WalletRepository;
import com.editay.bsps.utility.CommonUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {
   WalletRepository walletRepository;

   public WalletServiceImpl(WalletRepository walletRepository) {
      this.walletRepository = walletRepository;
   }

   public List<Wallet> getWallet() {
      List<Wallet> wallet = new ArrayList();
      this.walletRepository.findAll().forEach(wallet::add);
      return wallet;
   }

   public Wallet getWalletById(Long id) {
      return (Wallet)this.walletRepository.findById(id).get();
   }

   public void updateWalletById(BigDecimal walletAmount, Long id) {
      this.walletRepository.updateWalletById(walletAmount, id);
   }

   public Wallet insert(Wallet wallet) {
      return (Wallet)this.walletRepository.save(wallet);
   }

   public void updateWallet(Long id, Wallet wallet) {
      Wallet walletFromDb = (Wallet)this.walletRepository.findById(id).get();
      System.out.println(walletFromDb.toString());
      walletFromDb.setUsername(wallet.getUsername());
      walletFromDb.setWalletAmount(wallet.getWalletAmount());
      walletFromDb.setDateCreated(CommonUtil.todayDate());
      this.walletRepository.save(walletFromDb);
   }

   public void deleteWallet(Long id) {
      this.walletRepository.deleteById(id);
   }
}