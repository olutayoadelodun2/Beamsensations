package com.editay.bsps.service;

import com.editay.bsps.dto.PaystackTransactionRequestDto;
import com.editay.bsps.dto.PaystackTransactionResponseDto;

public interface PaystackInitializeTransactionService {
   PaystackTransactionResponseDto initializeTransaction(PaystackTransactionRequestDto paystackTransactionRequestDTO);
}
