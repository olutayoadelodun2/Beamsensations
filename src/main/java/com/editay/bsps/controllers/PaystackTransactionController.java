/* Decompiler 2ms, total 765ms, lines 30 */
package com.editay.bsps.controllers;

import com.editay.bsps.dto.PaystackTransactionRequestDto;
import com.editay.bsps.dto.PaystackTransactionResponseDto;
import com.editay.bsps.models.PeakSubscription;
import com.editay.bsps.service.PaystackInitializeTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 3600L)
@RestController
@RequestMapping({"/api/paystack"})
public class PaystackTransactionController {
   @Autowired
   private PaystackInitializeTransactionService initializeTransactionService;

   @RequestMapping(
      path = {"/initializetransaction"},
      method = {RequestMethod.POST}
   )
   public PaystackTransactionResponseDto initializeTransaction(@RequestBody PaystackTransactionRequestDto initializeTransactionRequestDTO) {
      new ClientController();
      new PeakSubscription();
      PaystackTransactionResponseDto initializeTransaction = this.initializeTransactionService.initializeTransaction(initializeTransactionRequestDTO);
      return initializeTransaction;
   }
}