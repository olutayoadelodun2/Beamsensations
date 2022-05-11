package com.editay.bsps.service;

import com.editay.bsps.dto.PaystackTransactionRequestDto;
import com.editay.bsps.dto.PaystackTransactionResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaystackTransactionServiceImpl implements PaystackInitializeTransactionService {
   RestTemplate restTemplate = new RestTemplate();

   public PaystackTransactionResponseDto initializeTransaction(PaystackTransactionRequestDto paystackTransactionRequestDto) {
      String url = "https://api.paystack.co/transaction/initialize";
      HttpHeaders headers = new HttpHeaders();
      String key = "sk_test_16c45f073f0f3633fc2b056120919dd87e33a2a3";
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set("Authorization", "Bearer " + key);
      HttpEntity<PaystackTransactionRequestDto> entity = new HttpEntity(paystackTransactionRequestDto, headers);
      ResponseEntity<PaystackTransactionResponseDto> response = this.restTemplate.postForEntity(url, entity, PaystackTransactionResponseDto.class, new Object[0]);
      return (PaystackTransactionResponseDto)response.getBody();
   }
}
