/* Decompiler 8ms, total 777ms, lines 76 */
package com.editay.bsps.controllers;

import com.editay.bsps.models.FreeAdCarDetails;
import com.editay.bsps.models.Wallet;
import com.editay.bsps.repository.FreeAdCarDetailsRepository;
import com.editay.bsps.repository.FreeAdRepository;
import com.editay.bsps.repository.UserRepository;
import com.editay.bsps.repository.WalletRepository;
import com.editay.bsps.service.FreeAdCarDetailsService;
import com.editay.bsps.service.WalletService;
import com.editay.bsps.utility.CommonUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 3600L)
@RestController
@RequestMapping({ "/api/wallet" })
public class WalletController {
	WalletService walletService;
	@Autowired
	WalletRepository walletRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	private FreeAdCarDetailsService freeAdCarDetailsService;

	@Autowired
	FreeAdCarDetailsRepository freeAdCarDetailsRepository;

	@Autowired
	FreeAdRepository freeAdRepository;

	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}

	@GetMapping
	public ResponseEntity<List<Wallet>> getAllWallets() {
		List<Wallet> wallets = this.walletService.getWallet();
		return new ResponseEntity(wallets, HttpStatus.OK);
	}

	@GetMapping({ "/{id}" })
	public ResponseEntity<Wallet> getWallet(@PathVariable Long id) {
		return new ResponseEntity(this.walletService.getWalletById(id), HttpStatus.OK);
	}

	@PostMapping({ "/add" })
	public ResponseEntity<Wallet> saveWallet(@RequestBody Wallet wallet) {
		wallet.setDateCreated(CommonUtil.todayDate());
		System.out.println("wallet.getId(): " + wallet.getId());
		System.out.println("wallet.getUsername(): " + wallet.getUsername());
		System.out.println(" wallet.getWalletAmount(): " + wallet.getWalletAmount());
		System.out.println("wallet.getDateCreated(): " + wallet.getDateCreated());
		Wallet wallet1 = this.walletService.insert(wallet);
		System.out.println("wallet1: " + wallet1);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("wallet", "/api/wallet" + Long.toString(wallet1.getId()));
		return new ResponseEntity(wallet1, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping({ "/{id}" })
	public ResponseEntity<Wallet> updateWallet(@PathVariable("id") Long id, @RequestBody Wallet wallet) {
		this.walletService.updateWallet(id, wallet);
		return new ResponseEntity(this.walletService.getWalletById(id), HttpStatus.OK);
	}

	@GetMapping({ "/username" })
	public ResponseEntity<List<Wallet>> getWalletByUsername(@RequestParam String username) {
		return new ResponseEntity(this.walletRepository.findByUsername(username), HttpStatus.OK);
	}

	
}