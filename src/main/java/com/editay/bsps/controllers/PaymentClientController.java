/* Decompiler 36ms, total 978ms, lines 398 */
package com.editay.bsps.controllers;

import com.editay.bsps.dto.HoistAdRequest;
import com.editay.bsps.dto.PaystackPaymentDto;
import com.editay.bsps.dto.PaystackPaymentDto2;
import com.editay.bsps.dto.PeakAdRequest;
import com.editay.bsps.dto.SeerBitDto;
import com.editay.bsps.repository.HoistAdRepository;
import com.editay.bsps.repository.PeakAdRepository;
import com.editay.bsps.repository.WalletRepository;
import com.editay.bsps.service.HoistAdService;
import com.editay.bsps.service.PeakAdService;
import com.editay.bsps.utility.Generator;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
   origins = {"*"},
   maxAge = 3600L
)
@RestController
@RequestMapping({"/api/payment"})
public class PaymentClientController {
   int responseEntity;
   PeakAdService peakAdService;
   HoistAdService hoistAdService;
   @Autowired
   PeakAdRepository peakAdRepository;
   @Autowired
   HoistAdRepository hoistAdRepository;
   @Autowired
   WalletRepository walletRepository;

   @PostMapping({"/seerbit/payments/peakAd"})
   public String makePaymentWithSeerBitForPeakAd(@RequestBody PaystackPaymentDto dto) throws URISyntaxException {
      ClientController cc = new ClientController();
      SeerBitDto seerbitDto = new SeerBitDto();
      JSONObject response = new JSONObject();
      System.out.println("");
      System.out.println("");
      System.out.println("");
      System.out.println("**********************************GET PEAKAD SELECTED****************************************************");
      String peakAdChoice = ClientController.getPeakAdId(dto.getPeakId());
      System.out.println("peakAdChoice: " + peakAdChoice);
      JSONObject peakAdChoiceObj = new JSONObject(peakAdChoice);
      long days = peakAdChoiceObj.getLong("days");
      long noOfPeak = peakAdChoiceObj.getLong("noOfPeak");
      BigDecimal amount = peakAdChoiceObj.getBigDecimal("amount");
      System.out.println("**********************************END GET PEAKAD SELECTED****************************************************");
      System.out.println("");
      System.out.println("");
      System.out.println("");
      System.out.println("**********************************ADD PEAKAD SUBSCRIPTION****************************************************");
      String peakAdsSubscription = cc.addPeakAdSubscription(days, noOfPeak, amount, dto.getUsername());
      System.out.println("peakAdsSubscription: " + peakAdsSubscription);
      JSONObject peakAdsSubscriptionObj = new JSONObject(peakAdsSubscription);
      String peakSubscriptionId = peakAdsSubscriptionObj.getString("peakId");
      System.out.println("**********************************END GET PEAKAD SUBSCRIPTION****************************************************");
      System.out.println("");
      System.out.println("");
      System.out.println("");
      System.out.println("**********************************GET PEAKAD SUBSCRIPTION DETAILS****************************************************");
      System.out.println("peakId: " + peakSubscriptionId);
      String peakAdDetails = ClientController.getPeakDetails(peakSubscriptionId).replace("[", "").replace("]", "");
      JSONObject peakDetailsObj = new JSONObject(peakAdDetails);
      System.out.println("**********************************END GET PEAKAD SUBSCRIPTION DETAILS****************************************************");
      System.out.println("");
      System.out.println("");
      System.out.println("");
      System.out.println("**********************************GET DEALER USER DETAILS****************************************************");
      String userDetails = ClientController.getUser(peakDetailsObj.getString("username"));
      System.out.println("userDetails: " + userDetails);
      JSONObject userDetailsObj = new JSONObject(userDetails);
      System.out.println("**********************************END GET DEALER USER DETAILS****************************************************");
      System.out.println("");
      System.out.println("");
      System.out.println("");
      System.out.println("**********************************GET KEY****************************************************");
      String seerbitResponse = cc.getSeerBitKey();
      System.out.println("Seerbit key response: " + seerbitResponse);
      JSONObject seerbitResponseObj = new JSONObject(seerbitResponse);
      String key = seerbitResponseObj.getJSONObject("data").getJSONObject("EncryptedSecKey").getString("encryptedKey");
      System.out.println("Seerbit encrypted key: " + key);
      System.out.println("**********************************GET KEY END****************************************************");
      System.out.println("");
      System.out.println("");
      System.out.println("");
      System.out.println("**********************************GET HASH****************************************************");
      seerbitDto.setPublicKey("SBTESTPUBK_NQolIN7JM833LDi8nC04yjVTAFNCdsq5");
      seerbitDto.setAmount(dto.getAmount());
      seerbitDto.setCurrency("NGN");
      seerbitDto.setCountry("NG");
      seerbitDto.setPaymentReference(Generator.getPaymentReference());
      System.out.println(seerbitDto.getPaymentReference());
      seerbitDto.setEmail(userDetailsObj.getString("email"));
      seerbitDto.setProductId(dto.getPeakId());
      seerbitDto.setProductDescription(peakAdsSubscriptionObj.getString("peakDescription"));
      seerbitDto.setCallBackUrl("http://beamsensations.com");
      String hashObj = cc.getSeerBitHash(seerbitDto);
      System.out.println("Seerbit hash object response: " + hashObj);
      JSONObject seerbitHashResponseObj = new JSONObject(hashObj);
      String hash = seerbitHashResponseObj.getJSONObject("data").getJSONObject("hash").getString("hash");
      System.out.println("hash: " + hash);
      System.out.println("**********************************END GET HASH****************************************************");
      System.out.println("");
      System.out.println("");
      System.out.println("");
      System.out.println("**********************************GET PAYMENT AND INSERT WALLET****************************************************");
      seerbitDto.setHash(hash);
      seerbitDto.setHashType("sha256");
      seerbitDto.setEncryptedKey(key);
      String seerbitPaymentResponse = cc.MakeSeerBitPayment(seerbitDto);
      JSONObject seerbitPaymentResponseObj = new JSONObject(seerbitPaymentResponse);
      String walletPayment = "";
      JSONObject walletPaymentobj = null;
      if (seerbitPaymentResponseObj.getString("status").equals("SUCCESS")) {
         BigDecimal peakAmount;
         String seerbitAmount;
         double walletAmount;
         if (!this.walletRepository.existsByUsername(peakDetailsObj.getString("username"))) {
            seerbitAmount = dto.getAmount();
            double seerbitAmount2 = Double.parseDouble(seerbitAmount);
            peakAmount = peakDetailsObj.getBigDecimal("amount");
            System.out.println("peakAmount: " + peakAmount);
            double peakAmount2 = peakAmount.doubleValue();
            System.out.println("peakAmount2: " + peakAmount2);
            walletAmount = seerbitAmount2 - peakAmount2;
            System.out.println("walletAmount: " + walletAmount);
            String walletAmount2 = String.valueOf(walletAmount);
            BigDecimal walletAmount3 = BigDecimal.valueOf(walletAmount);
            walletPayment = cc.walletPayment(peakDetailsObj.getString("username"), walletAmount3);
            System.out.println("walletPayment: " + walletPayment);
            new JSONObject(walletPayment);
            response.put("Response", "00");
            response.put("redirectLink", seerbitPaymentResponseObj.getJSONObject("data").getJSONObject("payments").getString("redirectLink"));
            response.put("peakAdSubscriptionId", peakSubscriptionId);
            response.put("responseMessage", "Kindly use the peakId subscription id to upload your cars");
         } else {
            seerbitAmount = ClientController.getWalletByUsername(peakDetailsObj.getString("username"));
            String userWalletDetails2 = seerbitAmount.replace("[", "").replace("]", "");
            System.out.println("userWalletDetails: " + userWalletDetails2);
            JSONObject userWalletObj = new JSONObject(userWalletDetails2);
            peakAmount = peakDetailsObj.getBigDecimal("amount");
            System.out.println("peakAmount: " + peakAmount);
            walletAmount = peakAmount.doubleValue();
            System.out.println("peakAmount3: " + walletAmount);
            BigDecimal walletBalance = userWalletObj.getBigDecimal("walletAmount");
            System.out.println("walletBalance: " + walletBalance);
            double walletBalance2 = walletBalance.doubleValue();
            System.out.println("walletBalance2: " + walletBalance2);
            double walletAmounts = walletBalance2 + walletAmount;
            System.out.println("walletAmount: " + walletAmounts);
            String wAmount = String.valueOf(walletAmounts);
            BigDecimal walletAmount2 = BigDecimal.valueOf(walletAmount);
            System.out.println("walletAmount2: " + walletAmount2);
            cc.updateWallet(userWalletObj.getString("username"), wAmount, userWalletObj.getLong("id"));
            response.put("Response", "00");
            response.put("msg", "SUCCESS");
            response.put("redirectLink", seerbitPaymentResponseObj.getJSONObject("data").getJSONObject("payments").getString("redirectLink"));
            response.put("peakAdSubscriptionId", peakSubscriptionId);
            response.put("responseMessage", "Kindly use the peakId subscription id to upload your cars");
         }
      } else {
         response.put("Response", "99");
         response.put("msg", "FAILED");
      }

      System.out.println("Seerbit payment response: " + seerbitPaymentResponse);
      System.out.println("**********************************END GET PAYMENT AND INSERT WALLET****************************************************");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("**********************************UPDATE WALLET****************************************************");
      return response.toString();
   }

   @PostMapping({"/paystack/initialization/peakAd"})
   public String makePaymentWithPaystackForPeakAd(@RequestBody PaystackPaymentDto dto) {
      ClientController pc = new ClientController();
      ClientController cc = new ClientController();
      System.out.println("**********************************GET PEAKAD SELECTED****************************************************");
      String peakAdChoice = ClientController.getPeakAdId(dto.getPeakId());
      System.out.println("peakAdChoice: " + peakAdChoice);
      JSONObject peakAdChoiceObj = new JSONObject(peakAdChoice);
      long days = peakAdChoiceObj.getLong("days");
      long noOfPeak = peakAdChoiceObj.getLong("noOfPeak");
      BigDecimal amount = peakAdChoiceObj.getBigDecimal("amount");
      System.out.println("**********************************ADD PEAKAD SUBSCRIPTION****************************************************");
      String peakAdsSubscription = cc.addPeakAdSubscription(days, noOfPeak, amount, dto.getUsername());
      System.out.println("peakAdsSubscription: " + peakAdsSubscription);
      JSONObject peakAdsSubscriptionObj = new JSONObject(peakAdsSubscription);
      String peakSubscriptionId = peakAdsSubscriptionObj.getString("peakId");
      System.out.println("**********************************GET PEAKAD SUBSCRIPTION DETAILS****************************************************");
      System.out.println("peakId: " + peakSubscriptionId);
      String peakAdDetails = ClientController.getPeakDetails(peakSubscriptionId).replace("[", "").replace("]", "");
      JSONObject peakDetailsObj = new JSONObject(peakAdDetails);
      System.out.println("**********************************GET DEALER EMAIL FROM USER DETAILS****************************************************");
      String userDetails = ClientController.getUser(peakDetailsObj.getString("username"));
      System.out.println("userDetails: " + userDetails);
      JSONObject userDetailsObj = new JSONObject(userDetails);
      System.out.println("**********************************INITIATE PAYSTACK PAYMENT****************************************************");
      dto.setEmail(userDetailsObj.getString("email"));
      String paymentDetails = pc.makePaystackPayment2(dto);
      JSONObject paymentDetailsObj2 = new JSONObject(paymentDetails);
      JSONObject paymentDetailsObj = new JSONObject(paymentDetailsObj2);
      paymentDetailsObj.put("Response", paymentDetailsObj2);
      paymentDetailsObj.put("peakAdSubscriptionId", peakSubscriptionId);
      paymentDetailsObj.put("responseMessage", "Kindly use the peakId subscription id to upload your cars");
      return paymentDetailsObj.toString();
   }

   @PostMapping({"/paystack/initialization/hoistAd"})
   public String makePaymentWithPaystackForHoistAd(@RequestBody PaystackPaymentDto2 dto) {
      ClientController pc = new ClientController();
      System.out.println("**********************************GET HOISTAD SUBSCRIPTION DETAILS****************************************************");
      String hoistAdDetails = ClientController.getHoistDetails(dto.getHoistId()).replace("[", "").replace("]", "");
      JSONObject hoistDetailsObj = new JSONObject(hoistAdDetails);
      System.out.println("**********************************GET DEALER EMAIL FROM USER DETAILS****************************************************");
      String userDetails = ClientController.getUser(hoistDetailsObj.getString("username"));
      System.out.println("userDetails: " + userDetails);
      JSONObject userDetailsObj = new JSONObject(userDetails);
      System.out.println("**********************************INITIATE PAYSTACK PAYMENT****************************************************");
      dto.setEmail(userDetailsObj.getString("email"));
      String paymentDetails = pc.makePaystackPayment3(dto);
      return paymentDetails;
   }

   @GetMapping({"/paystack/peakad/{reference}/{peakAdSubscriptionId}"})
   public String vrifyPaystackTransactionForPeakAd(@PathVariable String reference, @PathVariable String peakAdSubscriptionId) throws JSONException, Exception {
      JSONObject response = new JSONObject();
      ClientController pc = new ClientController();
      String verify = ClientController.verifyPaystackTransaction(reference);
      JSONObject verifyObj = new JSONObject(verify);
      System.out.println("verifyObj: " + verifyObj);
      if (verifyObj.getJSONObject("data").getString("gateway_response").equals("Successful") && verifyObj.getJSONObject("data").getString("status").equals("success")) {
         String peakDetails = ClientController.getPeakDetails(peakAdSubscriptionId);
         String peakDetails2 = peakDetails.replace("[", "").replace("]", "");
         System.out.println("peakDetails2: " + peakDetails2);
         PeakAdRequest peakAd = new PeakAdRequest();
         JSONObject peakDetailsObj = new JSONObject(peakDetails2);
         if (peakDetailsObj.getString("peakId").equals(peakAdSubscriptionId)) {
            peakAd.setId(peakDetailsObj.getLong("id"));
            peakAd.setAmount(peakDetailsObj.getBigDecimal("amount"));
            peakAd.setBalance(String.valueOf(peakAd.getAmount()));
            peakAd.setPeakId(peakAdSubscriptionId);
            String updated = pc.updatePeakAd(peakAd);
            System.out.println(updated);
            String walletPayment = "";
            JSONObject walletPaymentobj = null;
            BigDecimal peakAmount;
            double peakAmount2;
            if (!this.walletRepository.existsByUsername(peakDetailsObj.getString("username"))) {
               BigDecimal paystackAmount = BigDecimal.valueOf(verifyObj.getJSONObject("data").getLong("requested_amount"));
               System.out.println("paystackAmount: " + paystackAmount);
               System.out.println("paystackAmount.doubleValue(): " + paystackAmount.doubleValue());
               double paystackAmount2 = paystackAmount.doubleValue() / 100.0D;
               System.out.println("paystackAmount2: " + paystackAmount2);
               peakAmount = peakDetailsObj.getBigDecimal("amount");
               System.out.println("peakAmount: " + peakAmount);
               peakAmount2 = peakAmount.doubleValue();
               System.out.println("peakAmount2: " + peakAmount2);
               double walletAmount = paystackAmount2 - peakAmount2;
               System.out.println("walletAmount: " + walletAmount);
               BigDecimal walletAmount2 = BigDecimal.valueOf(walletAmount);
               System.out.println("walletAmount2: " + walletAmount2);
               walletPayment = pc.walletPayment(peakDetailsObj.getString("username"), walletAmount2);
               System.out.println("walletPayment: " + walletPayment);
               walletPaymentobj = new JSONObject(walletPayment);
               response.put("dealer_wallet_id", walletPaymentobj.getLong("id"));
            } else {
               String userWalletDetails = ClientController.getWalletByUsername(peakDetailsObj.getString("username"));
               String userWalletDetails2 = userWalletDetails.replace("[", "").replace("]", "");
               System.out.println("userWalletDetails: " + userWalletDetails2);
               JSONObject userWalletObj = new JSONObject(userWalletDetails2);
               peakAmount = BigDecimal.valueOf(verifyObj.getJSONObject("data").getLong("requested_amount"));
               System.out.println("paystackAmount: " + peakAmount);
               System.out.println("paystackAmount.doubleValue(): " + peakAmount.doubleValue());
               peakAmount2 = peakAmount.doubleValue() / 100.0D;
               System.out.println("paystackAmount2: " + peakAmount2);
               BigDecimal walletBalance = userWalletObj.getBigDecimal("walletAmount");
               System.out.println("walletBalance: " + walletBalance);
               double walletBalance2 = walletBalance.doubleValue();
               System.out.println("walletBalance2: " + walletBalance2);
               double walletAmount = walletBalance2 + peakAmount2;
               System.out.println("walletAmount: " + walletAmount);
               String wAmount = String.valueOf(walletAmount);
               BigDecimal walletAmount2 = BigDecimal.valueOf(walletAmount);
               System.out.println("walletAmount2: " + walletAmount2);
               pc.updateWallet(userWalletObj.getString("username"), wAmount, userWalletObj.getLong("id"));
               response.put("dealer_wallet_id", userWalletObj.getLong("id"));
            }

            response.put("gateway_response", verifyObj.getJSONObject("data").getString("gateway_response"));
            response.put("status", verifyObj.getJSONObject("data").getString("status"));
         } else {
            response.put("gateway_response", "wrong peak id");
            response.put("gateway_response", "wrong peak id");
         }
      } else {
         response.put("gateway_response", verifyObj.getJSONObject("data").getString("gateway_response"));
         response.put("gateway_response", verifyObj.getJSONObject("data").getString("status"));
      }

      return response.toString();
   }

   @GetMapping({"/paystack/hoistad/{reference}/{hoistId}"})
   public String vrifyPaystackTransactionForHoistAd(@PathVariable String reference, @PathVariable String hoistId) throws JSONException, Exception {
      JSONObject response = new JSONObject();
      ClientController pc = new ClientController();
      String verify = ClientController.verifyPaystackTransaction(reference);
      JSONObject verifyObj = new JSONObject(verify);
      System.out.println("verifyObj: " + verifyObj);
      if (verifyObj.getJSONObject("data").getString("gateway_response").equals("Successful") && verifyObj.getJSONObject("data").getString("status").equals("success")) {
         String hoistDetails = ClientController.getHoistDetails(hoistId);
         String hoistDetails2 = hoistDetails.replace("[", "").replace("]", "");
         System.out.println("hoistDetails2: " + hoistDetails2);
         HoistAdRequest hoistAd = new HoistAdRequest();
         JSONObject hoistDetailsObj = new JSONObject(hoistDetails2);
         if (hoistDetailsObj.getString("hoistId").equals(hoistId)) {
            hoistAd.setId(hoistDetailsObj.getLong("id"));
            hoistAd.setAmount(hoistDetailsObj.getBigDecimal("amount"));
            hoistAd.setBalance(String.valueOf(hoistAd.getAmount()));
            hoistAd.setHoistId(hoistId);
            String updated = pc.updateHoistAd(hoistAd);
            System.out.println(updated);
            String walletPayment = "";
            JSONObject walletPaymentobj = null;
            BigDecimal hoistAmount;
            double hoistAmount2;
            if (!this.walletRepository.existsByUsername(hoistDetailsObj.getString("username"))) {
               BigDecimal paystackAmount = BigDecimal.valueOf(verifyObj.getJSONObject("data").getLong("requested_amount"));
               System.out.println("paystackAmount: " + paystackAmount);
               System.out.println("paystackAmount.doubleValue(): " + paystackAmount.doubleValue());
               double paystackAmount2 = paystackAmount.doubleValue() / 100.0D;
               System.out.println("paystackAmount2: " + paystackAmount2);
               hoistAmount = hoistDetailsObj.getBigDecimal("amount");
               System.out.println("hoistAmount: " + hoistAmount);
               hoistAmount2 = hoistAmount.doubleValue();
               System.out.println("hoistAmount2: " + hoistAmount2);
               double walletAmount = paystackAmount2 - hoistAmount2;
               System.out.println("walletAmount: " + walletAmount);
               BigDecimal walletAmount2 = BigDecimal.valueOf(walletAmount);
               System.out.println("walletAmount2: " + walletAmount2);
               walletPayment = pc.walletPayment(hoistDetailsObj.getString("username"), walletAmount2);
               System.out.println("walletPayment: " + walletPayment);
               walletPaymentobj = new JSONObject(walletPayment);
               response.put("dealer_wallet_id", walletPaymentobj.getLong("id"));
            } else {
               String userWalletDetails = ClientController.getWalletByUsername(hoistDetailsObj.getString("username"));
               String userWalletDetails2 = userWalletDetails.replace("[", "").replace("]", "");
               System.out.println("userWalletDetails: " + userWalletDetails2);
               JSONObject userWalletObj = new JSONObject(userWalletDetails2);
               hoistAmount = BigDecimal.valueOf(verifyObj.getJSONObject("data").getLong("requested_amount"));
               System.out.println("paystackAmount: " + hoistAmount);
               System.out.println("paystackAmount.doubleValue(): " + hoistAmount.doubleValue());
               hoistAmount2 = hoistAmount.doubleValue() / 100.0D;
               System.out.println("paystackAmount2: " + hoistAmount2);
               BigDecimal walletBalance = userWalletObj.getBigDecimal("walletAmount");
               System.out.println("walletBalance: " + walletBalance);
               double walletBalance2 = walletBalance.doubleValue();
               System.out.println("walletBalance2: " + walletBalance2);
               double walletAmount = walletBalance2 + hoistAmount2;
               String wAmount = String.valueOf(walletAmount);
               System.out.println("walletAmount: " + walletAmount);
               BigDecimal walletAmount2 = BigDecimal.valueOf(walletAmount);
               System.out.println("walletAmount2: " + walletAmount2);
               pc.updateWallet(userWalletObj.getString("username"), wAmount, userWalletObj.getLong("id"));
               response.put("dealer_wallet_id", userWalletObj.getLong("id"));
            }

            response.put("gateway_response", verifyObj.getJSONObject("data").getString("gateway_response"));
            response.put("status", verifyObj.getJSONObject("data").getString("status"));
         } else {
            response.put("gateway_response", "wrong peak id");
            response.put("gateway_response", "wrong peak id");
         }

         response = verifyObj;
      } else {
         response.put("gateway_response", verifyObj.getJSONObject("data").getString("gateway_response"));
         response.put("gateway_response", verifyObj.getJSONObject("data").getString("status"));
      }

      return response.toString();
   }
}