/* Decompiler 49ms, total 1013ms, lines 524 */
package com.editay.bsps.controllers;

import com.editay.bsps.dto.HoistAdRequest;
import com.editay.bsps.dto.PaystackPaymentDto;
import com.editay.bsps.dto.PaystackPaymentDto2;
import com.editay.bsps.dto.PeakAdRequest;
import com.editay.bsps.dto.SeerBitDto;
import com.editay.bsps.dto.WalletDto;
import com.editay.bsps.models.PeakSubscription;
import com.editay.bsps.models.Wallet;
import com.editay.bsps.utility.Constants;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ClientController {
	static RestTemplate restTemplate = new RestTemplate();

	public String getSeerBitKey() throws URISyntaxException {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		// String uri = "https://seerbitapi.com/api/v2/encrypt/keys";
		String uri = Constants.SEER_BIT_KEY_URL;
		SeerBitDto key = new SeerBitDto();
		// key.setKey("SBTESTSECK_MTeGLiDybyTtBihwZpnciGGxGzzufL4f8mdSUNPb.SBTESTPUBK_NQolIN7JM833LDi8nC04yjVTAFNCdsq5");
		key.setKey(Constants.SEER_BIT_SECRET_KEY + "." + Constants.SEER_BIT_PUBLIC_KEY);
		HttpEntity<SeerBitDto> requestEntity = new HttpEntity(key, requestHeaders);
		return (String) restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class, new Object[0])
				.getBody();
	}

	public String getSeerBitHash(SeerBitDto key) throws URISyntaxException {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		// String uri = "https://seerbitapi.com/api/v2/encrypt/hashs";
		String uri = Constants.SEER_BIT_HASH_URL;
		SeerBitDto key2 = new SeerBitDto(key.getPublicKey(), key.getAmount(), key.getCurrency(), key.getCountry(),
				key.getPaymentReference(), key.getEmail(), key.getProductId(), key.getProductDescription(),
				key.getCallBackUrl());
		HttpEntity<SeerBitDto> requestEntity = new HttpEntity(key2, requestHeaders);
		return (String) restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class, new Object[0])
				.getBody();
	}

	public String MakeSeerBitPayment(SeerBitDto key) throws URISyntaxException {
		HttpHeaders requestHeaders = new HttpHeaders();
		String auth = "Bearer " + key.getEncryptedKey();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.add("Authorization", auth);
		// String uri = "https://seerbitapi.com/api/v2/payments";
		String uri = Constants.SEER_BIT_PAYMENT_URL;
		SeerBitDto key2 = new SeerBitDto(key.getPublicKey(), key.getAmount(), key.getCurrency(), key.getCountry(),
				key.getPaymentReference(), key.getEmail(), key.getProductId(), key.getProductDescription(),
				key.getCallBackUrl(), key.getHash(), key.getHashType());
		HttpEntity<SeerBitDto> requestEntity = new HttpEntity(key2, requestHeaders);
		return (String) restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class, new Object[0])
				.getBody();
	}

	public String makePaystackPayment(PeakSubscription ps) {
		// String url =
		// "http://api.beamsensations.com/api/peakAds/initializetransaction";
		String url = Constants.PROD_BASE_URL + "peakAds/initializetransaction";
		String requestJson = "{\n\n   \"amount\" :  \"" + ps.getAmount() + "\",\n   \"email\"  :  \"" + ps.getEmail()
				+ "\"\n}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity(requestJson.toString(), headers);
		String response = (String) restTemplate.postForObject(url, entity, String.class, new Object[0]);
		System.out.println("response:  " + response);
		return response;
	}

	public String addPeakAdSubscription(long days, long noOfPeak, BigDecimal amount, String username) {
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("timestamp: " + timestamp);
		String subscriptionDate = sdf3.format(timestamp);
		System.out.println("subscriptionDate: " + subscriptionDate);
		// String url = "http://api.beamsensations.com/api/peakAds/peakAd";
		String url = Constants.PROD_BASE_URL + "peakAds/peakAd";
		JSONObject obj = new JSONObject();
		obj.put("days", days);
		obj.put("noOfPeak", noOfPeak);
		obj.put("amount", amount);
		obj.put("username", username);
		obj.put("subscriptionDate", subscriptionDate);
		String requestJson = obj.toString();
		System.out.println("pead ad subscription JSON request: " + obj);
		System.out.println("pead ad subscription converted request: " + requestJson);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity(requestJson, headers);
		String response = (String) restTemplate.postForObject(url, entity, String.class, new Object[0]);
		System.out.println("response:  " + response);
		return response;
	}

	public String walletPayment(String username, BigDecimal walletAmount) {
		// String url = "http://api.beamsensations.com/api/wallet/add";
		String url = Constants.PROD_BASE_URL + "wallet/add";
		String requestJson = "{\n\n   \"username\" :  \"" + username + "\",\n   \"walletAmount\"  :  \"" + walletAmount
				+ "\"\n}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity(requestJson, headers);
		String response = (String) restTemplate.postForObject(url, entity, String.class, new Object[0]);
		System.out.println("response:  " + response);
		return response;
	}

	public void updateWallet2(String username, BigDecimal walletAmount, long id) throws Exception {
		// String URL = "http://api.beamsensations.com/api/wallet/{id}";
		String URL = Constants.PROD_BASE_URL + "wallet/{id}";
		System.out.println("wallet username: " + username);
		System.out.println("wallet amount: " + walletAmount);
		System.out.println("wallet id: " + id);
		Map<String, String> params = new HashMap();
		String id2 = String.valueOf(id);
		params.put("id", id2);
		Wallet updatedWallet = new Wallet(username, walletAmount);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(URL, updatedWallet, params);
	}

	public void updateWallet(String username, String walletAmount, long id) throws URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		// URI uri = new URI("http://api.beamsensations.com/api/wallet/" + id);
		URI uri = new URI(Constants.PROD_BASE_URL + "/wallet/" + id);
		BigDecimal decimal = new BigDecimal(String.valueOf(walletAmount));
		WalletDto objEmp = new WalletDto("Peter", decimal);
		RequestEntity<WalletDto> requestEntity = new RequestEntity(objEmp, headers, HttpMethod.PUT, uri);
		ParameterizedTypeReference<WalletDto> typeRef = new ParameterizedTypeReference<WalletDto>() {
		};
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<WalletDto> responseEntity = restTemplate.exchange(requestEntity, typeRef);
		System.out.println("wallet update Status Code: " + responseEntity.getStatusCode());
		System.out.println("wallet update response body: " + responseEntity.getBody());
	}

	public String updateWallet3(String username, BigDecimal walletAmount, long id) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		// String uri = "http://api.beamsensations.com/api/wallet/" + id;
		String uri = Constants.PROD_BASE_URL + "wallet/" + id;
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		WalletDto wallet = new WalletDto(username, walletAmount, id);
		HttpEntity<WalletDto> entity = new HttpEntity(wallet, headers);
		return (String) restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class, new Object[0]).getBody();
	}

	public String makePaystackPayment2(PaystackPaymentDto dto) {
		String amount = dto.getAmount() + "00";
		// String url =
		// "http://api.beamsensations.com/api/peakAds/initializetransaction";
		String url = Constants.PROD_BASE_URL + "peakAds/initializetransaction";
		String requestJson = "{\n\n   \"amount\" :  \"" + amount + "\",\n   \"email\"  :  \"" + dto.getEmail()
				+ "\"\n}";
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity(requestJson, headers);
		String response = (String) restTemplate.postForObject(url, entity, String.class, new Object[0]);
		System.out.println("response:  " + response);
		return response;
	}

	public String makePaystackPayment3(PaystackPaymentDto2 dto) {
		String amount = dto.getAmount() + "00";
		// String url =
		// "http://api.beamsensations.com/api/peakAds/initializetransaction";
		String url = Constants.PROD_BASE_URL + "peakAds/initializetransaction";
		String requestJson = "{\n\n   \"amount\" :  \"" + amount + "\",\n   \"email\"  :  \"" + dto.getEmail()
				+ "\"\n}";
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity(requestJson, headers);
		String response = (String) restTemplate.postForObject(url, entity, String.class, new Object[0]);
		System.out.println("response:  " + response);
		return response;
	}

	public String makePaystackPayment3(PaystackPaymentDto dto) {
		String amount = dto.getAmount() + "00";
		// String url =
		// "http://api.beamsensations.com/api/hoistAds/initializetransaction";
		String url = Constants.PROD_BASE_URL + "hoistAds/initializetransaction";
		String requestJson = "{\n\n   \"amount\" :  \"" + amount + "\",\n   \"email\"  :  \"" + dto.getEmail()
				+ "\"\n}";
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity(requestJson, headers);
		String response = (String) restTemplate.postForObject(url, entity, String.class, new Object[0]);
		System.out.println("response:  " + response);
		return response;
	}

	public String getDealersFullDetails(String username, String peakId, String token) {
		String userDetails = "";
		String kycDetails = "";
		JSONObject response = new JSONObject();
		String peakDetails = getPeakDetails(peakId).replace("[", "").replace("]", "");
		JSONObject peakDetailsObj = new JSONObject(peakDetails);
		if (peakDetailsObj.getString("peakId").equals(peakId)) {
			if (peakDetailsObj.getString("username").equals(username)) {
				userDetails = getUser(username, token);
				JSONObject userDetailsObj = new JSONObject(userDetails);
				kycDetails = getKYC(username, token);
				String kycDetails2 = kycDetails.replace("[", "").replace("]", "");
				JSONObject kycDetailsObj = new JSONObject(kycDetails2);
				response.put("username", userDetailsObj.getString("username"));
				response.put("roles", userDetailsObj.getJSONArray("roles"));
				response.put("email", userDetailsObj.getString("email"));
				response.put("firstname", userDetailsObj.getString("firstname"));
				response.put("middlename", userDetailsObj.getString("middlename"));
				response.put("surname", userDetailsObj.getString("surname"));
				response.put("phonenumber", userDetailsObj.getString("phonenumber"));
				response.put("mobile", userDetailsObj.getString("mobile"));
				response.put("address", userDetailsObj.getString("address"));
				response.put("city", userDetailsObj.getString("city"));
				response.put("state", userDetailsObj.getString("state"));
				response.put("zipcode", userDetailsObj.getString("zipcode"));
				response.put("title", userDetailsObj.getString("title"));
				response.put("nin", kycDetailsObj.getString("nin"));
				response.put("accountNumber", kycDetailsObj.getString("accountNumber"));
				response.put("bankName", kycDetailsObj.getString("bankName"));
				response.put("passport", kycDetailsObj.getString("passport"));
				response.put("cacDocument", kycDetailsObj.getString("cacDocument"));
				response.put("brand", kycDetailsObj.getString("brand"));
				response.put("peakDescription", peakDetailsObj.getString("peakDescription"));
				response.put("days", peakDetailsObj.getLong("days"));
				response.put("noOfPeak", peakDetailsObj.getLong("noOfPeak"));
				response.put("amount", peakDetailsObj.getBigDecimal("amount"));
				response.put("accountStatus", peakDetailsObj.getString("accountStatus"));
				response.put("balance", peakDetailsObj.getString("balance"));
				response.put("peakId", peakDetailsObj.getString("peakId"));
				response.put("subscriptionDate", peakDetailsObj.getString("subscriptionDate"));
				response.put("subscriptionExpireDate", peakDetailsObj.getString("subscriptionExpireDate"));
			} else {
				response.put("message", "wrong username");
			}
		} else {
			response.put("message", "wrong peakAd Id");
		}

		return response.toString();
	}
	
	
	public static String getFreeAdById2(int freeadId, String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/peakAds/peakId?peakId=" +
		// peakId;
		String theUrl = Constants.PROD_BASE_URL + "freeAds/freeads/" + freeadId;
		System.out.println("theUrl: " + theUrl);
		System.out.println("freeadId: " + freeadId);
		headers.set("Authorization", accessToken);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}
	
	public static String getFreeAdCaruploadById(Long caruploadId, String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/peakAds/peakId?peakId=" +
		// peakId;
		String theUrl = Constants.PROD_BASE_URL + "freeAdsCarUpload/caruploads/" + caruploadId;
		System.out.println("theUrl: " + theUrl);
		System.out.println("carupload: " + caruploadId);
		headers.set("Authorization", accessToken);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}


	public static String getFreeAdById(String freeadId, String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/peakAds/peakId?peakId=" +
		// peakId;
		String theUrl = Constants.PROD_BASE_URL + "freeAds/freead/" + freeadId;
		System.out.println("theUrl: " + theUrl);
		System.out.println("freeadId: " + freeadId);
		headers.set("Authorization", "Bearer " + accessToken);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}
	
	
	public static String singleApprovedFreeAd(int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl 
		String theUrl = Constants.PROD_BASE_URL + "freeAds/publishASingleFreead/" + id;
		System.out.println("theUrl: " + theUrl);
		System.out.println("id: " + id);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}
	
	public static String singleCarUpload(String freeadId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl 
		String theUrl = Constants.PROD_BASE_URL + "freeAdsCarUpload/freeAdId?freeAdId=" + freeadId;
		System.out.println("theUrl: " + theUrl);
		System.out.println("id: " + freeadId);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}
	
	public static String singleCarUpload2() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl 
		String theUrl = Constants.PROD_BASE_URL + "freeAdsCarUpload/freeAdId?freeAdId=F2262";
		System.out.println("theUrl: " + theUrl);
		//System.out.println("id: " + freeadId);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			System.out.println("response: " + response);
			response2 = (String) response.getBody();
			System.out.println("response2: " + response2);
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}
	
	
	
	
	public static String publishFreeadCarupload(int id) {
		
		String response = "";
		String response2 = "";
		JSONObject respObj = new JSONObject();
		JSONObject respObj2 = new JSONObject();
		
		
		
		try {
			
			JSONObject obj = new JSONObject(singleApprovedFreeAd(id));
			System.out.println("obj: " + obj);
			String freeadId = obj.getString("freeAdId");
			System.out.println("freeadId: " + freeadId);
			JSONObject obj2 = new JSONObject(singleCarUpload(freeadId));
			System.out.println("obj2: " + obj2);
			respObj2.put("carDetails", obj2);
			respObj.put("freeDescription", obj.getString("freeDescription"));
			respObj.put("noOfDays", obj.getInt("noOfDays"));
			respObj.put("accountStatus", obj.getString("accountStatus"));
			respObj.put("subscriptionDate", obj.getString("subscriptionDate"));
			respObj.put("subscriptionExpireDate", obj.getString("subscriptionExpireDate"));
			respObj2.put("subscriptionDetails", respObj);
			response = respObj2.toString();
			System.out.println("response: " + response);
			
			
			
		} catch(Exception ex) {
			
			respObj.put("exception", ex.getMessage());
			
			response = respObj.toString();
		}
		
		return response;
		
		
	}
	
	public static String getASingleSingleUserByAdmin(String username,String auth) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", auth);
		String response2 = "";
		String theUrl = Constants.PROD_BASE_URL + "users/username3?username=" + username;
		System.out.println("theUrl: " + theUrl);
		System.out.println("username: " + username);
		System.out.println("auth: " + auth);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			System.out.println("response  " + response);
			response2 = (String) response.getBody();
			System.out.println("response 2 " + response2);
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}

	public static String getFreeAdById2(String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/peakAds/peakId?peakId=" +
		// peakId;
		String theUrl = Constants.PROD_BASE_URL + "freeAdsCarUpload/" + id;
		System.out.println("theUrl: " + theUrl);
		System.out.println("id: " + id);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}

	public String getFreeAdCartobePublished(String id, String accessToken) {

		JSONObject response = new JSONObject();
		try {
			String freeadDetailsById = getFreeAdById2(id).replace("[", "").replace("]", "");
			System.out.println("freeadDetailsById: " + freeadDetailsById);
			JSONObject freeadDetailsByIdObj = new JSONObject(freeadDetailsById);
			String freeAdId = freeadDetailsByIdObj.getString("freeAdId");
			System.out.println("freeAdId: " + freeAdId);
			String freeadDetailsById2 = getFreeAdById(freeAdId, accessToken).replace("[", "").replace("]", "");
			System.out.println("freeadDetailsById2: " + freeadDetailsById2);
			JSONObject freeadDetailsByIdObj2 = new JSONObject(freeadDetailsById2);

			response.put("responseCode", "00");
			response.put("responseMessage", "SUCCESS");
			response.put("id", freeadDetailsByIdObj.getInt("id"));
			response.put("engineType", freeadDetailsByIdObj.getString("engineType"));
			response.put("fuelType", freeadDetailsByIdObj.getString("fuelType"));
			response.put("transmission", freeadDetailsByIdObj.getString("transmission"));
			response.put("mileage", freeadDetailsByIdObj.getString("mileage"));
			response.put("engineCapacity", freeadDetailsByIdObj.getString("engineCapacity"));
			response.put("customPapers", freeadDetailsByIdObj.getString("customPapers"));
			response.put("exteriorColour", freeadDetailsByIdObj.getString("exteriorColour"));
			response.put("interiorColour", freeadDetailsByIdObj.getString("interiorColour"));
			response.put("driverType", freeadDetailsByIdObj.getString("driverType"));
			response.put("diagnostics", freeadDetailsByIdObj.getString("diagnostics"));
			response.put("airbag", freeadDetailsByIdObj.getString("airbag"));
			response.put("carPrice", freeadDetailsByIdObj.getString("carPrice"));
			response.put("vin", freeadDetailsByIdObj.getString("vin"));
			response.put("vehicleId", freeadDetailsByIdObj.getString("vehicleId"));
			response.put("model", freeadDetailsByIdObj.getString("model"));
			response.put("make", freeadDetailsByIdObj.getString("make"));
			response.put("description", freeadDetailsByIdObj.getString("description"));
			response.put("freeAdId", freeadDetailsByIdObj.getString("freeAdId"));
			response.put("username", freeadDetailsByIdObj.getString("username"));
			response.put("accessToken", freeadDetailsByIdObj.getString("accessToken"));
			response.put("date", freeadDetailsByIdObj.getString("date"));
			response.put("accountStatus", freeadDetailsByIdObj2.getString("accountStatus"));
			response.put("subscriptionDate", freeadDetailsByIdObj2.getString("subscriptionDate"));
			response.put("subscriptionExpireDate", freeadDetailsByIdObj2.getString("subscriptionExpireDate"));
			response.put("carImage", freeadDetailsByIdObj.getJSONObject("freeAdCarUpload").getString("carImage"));
		} catch (Exception ex) {

			response.put("statusCode", "01");
			response.put("statusMessage", "FAILED");
			response.put("failureMessage", ex.getMessage());
		}

		return response.toString();
	}

	public static String getPeakDetails(String peakId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/peakAds/peakId?peakId=" +
		// peakId;
		String theUrl = Constants.PROD_BASE_URL + "peakAds/peakId?peakId=" + peakId;
		System.out.println("theUrl: " + theUrl);
		System.out.println("peakId: " + peakId);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}

	public static String getWalletById(String walletId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/wallet/" + walletId;
		String theUrl = Constants.PROD_BASE_URL + "wallet/" + walletId;
		System.out.println("theUrl: " + theUrl);
		System.out.println("peakId: " + walletId);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}

	public static String getWalletByUsername(String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/wallet/username?username="
		// + username;
		String theUrl = Constants.PROD_BASE_URL + "wallet/username?username=" + username;
		System.out.println("theUrl: " + theUrl);
		System.out.println("peakId: " + username);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}

	public static String getPeakEmail(String email) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/peakAds/email?email=" +
		// email;
		String theUrl = Constants.PROD_BASE_URL + "peakAds/email?email=" + email;
		System.out.println("theUrl: " + theUrl);
		System.out.println("peakId: " + email);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}

	public static String getDealerDetails(String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl =
		// "http://api.beamsensations.com/api/peakAds/username?username=" + username;
		String theUrl = Constants.PROD_BASE_URL + "peakAds/username?username=" + username;
		System.out.println("theUrl: " + theUrl);
		System.out.println("peakId: " + username);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}

	public static String getHoistDetails(String hoistId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/hoistAds/hoistId?hoistId="
		// + hoistId;
		String theUrl = Constants.PROD_BASE_URL + "hoistAds/hoistId?hoistId=" + hoistId;
		System.out.println("theUrl: " + theUrl);
		System.out.println("peakId: " + hoistId);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}

	public static String getHoistEmail(String email) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/hoistAds/email?email=" +
		// email;
		String theUrl = Constants.PROD_BASE_URL + "hoistAds/email?email=" + email;
		System.out.println("theUrl: " + theUrl);
		System.out.println("peakId: " + email);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}

	public static String getUser(String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/peakAds/user?username=" +
		// username;
		String theUrl = Constants.PROD_BASE_URL + "peakAds/user?username=" + username;
		System.out.println("theUrl: " + theUrl);
		System.out.println("peakId: " + username);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}

	public static String getPeakAdId(String peakId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/peakAds2/" + peakId;
		String theUrl = Constants.PROD_BASE_URL + "api/peakAds2/" + peakId;
		System.out.println("theUrl: " + theUrl);
		System.out.println("peakId: " + peakId);

		try {
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}

	public static String getKYC(String username, String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/kyc/username?username=" +
		// username;
		String theUrl = Constants.PROD_BASE_URL + "kyc/username?username=" + username;

		try {
			headers.set("Authorization", accessToken);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var7) {
			System.out.println("** Exception: " + var7.getMessage());
		}

		return response2;
	}

	public static String getFreeAdDetails(String freeAdId, String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/kyc/username?username=" +
		// username;
		String theUrl = Constants.PROD_BASE_URL + "freeAds/freead2" + "?" + "freeAdId=" + freeAdId;

		try {
			// headers.set("Authorization", accessToken);
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var7) {
			System.out.println("** Exception: " + var7.getMessage());
		}

		return response2;
	}

	public static String verifyPaystackTransaction(String reference) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		String theUrl = "https://api.paystack.co/transaction/verify/" + reference;

		try {
			headers.set("Authorization", "Bearer sk_test_16c45f073f0f3633fc2b056120919dd87e33a2a3");
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
			System.out.println("response 2: " + response2);
		} catch (Exception var6) {
			System.out.println("** Exception: " + var6.getMessage());
		}

		return response2;
	}

	public static String getUser(String username, String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/users/username?username="
		// + username;
		String theUrl = Constants.PROD_BASE_URL + "users/username?username=" + username;

		try {
			headers.set("Authorization", accessToken);
			HttpEntity<String> entity = new HttpEntity("parameters", headers);
			ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class,
					new Object[0]);
			response2 = (String) response.getBody();
		} catch (Exception var7) {
			System.out.println("** Exception: " + var7.getMessage());
		}

		return response2;
	}

	public String updatePeakAd2(PeakAdRequest peakAd) throws URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// URI uri = new URI("http://api.beamsensations.com/api/peakAds/update/status/"
		// + peakAd.getId());
		URI uri = new URI(Constants.PROD_BASE_URL + "peakAds/update/status/" + peakAd.getId());
		System.out.println(" uri: " + uri);
		PeakAdRequest peakAd2 = new PeakAdRequest(peakAd.getId(), peakAd.getBalance(), peakAd.getPeakId());
		HttpEntity<PeakAdRequest> httpEntity = new HttpEntity(peakAd2, headers);
		ResponseEntity<PeakAdRequest> responseEntity = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity,
				PeakAdRequest.class);
		System.out.println("Status Code: " + responseEntity.getStatusCode());
		System.out.println("responseEntity.getBody(): " + responseEntity.getBody());
		return ((PeakAdRequest) responseEntity.getBody()).toString();
	}

	public String updatePeakAd(PeakAdRequest peakAd) throws URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		// URI uri = new URI("http://api.beamsensations.com/api/peakAds/update/status/"
		// + peakAd.getId());
		URI uri = new URI(Constants.PROD_BASE_URL + "peakAds/update/status/" + peakAd.getId());
		System.out.println(" uri: " + uri);
		PeakAdRequest peakAd2 = new PeakAdRequest(peakAd.getId(), peakAd.getBalance(), peakAd.getPeakId());
		RequestEntity<PeakAdRequest> requestEntity = new RequestEntity(peakAd2, headers, HttpMethod.PUT, uri);
		ParameterizedTypeReference<PeakAdRequest> typeRef = new ParameterizedTypeReference<PeakAdRequest>() {
		};
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<PeakAdRequest> responseEntity = restTemplate.exchange(requestEntity, typeRef);
		System.out.println("PeakAd update Status Code: " + responseEntity.getStatusCode());
		System.out.println("PeakAd update response body: " + responseEntity.getBody());
		return ((PeakAdRequest) responseEntity.getBody()).toString();
	}

	public String updateHoistAd(HoistAdRequest hoistAd) throws URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// URI uri = new URI("http://api.beamsensations.com/api/hoistAds/update/status/"
		// + hoistAd.getId());
		URI uri = new URI(Constants.PROD_BASE_URL + "hoistAds/update/status/" + hoistAd.getId());
		System.out.println(" uri: " + uri);
		PeakAdRequest peakAd2 = new PeakAdRequest(hoistAd.getId(), hoistAd.getBalance(), hoistAd.getHoistId());
		HttpEntity<PeakAdRequest> httpEntity = new HttpEntity(peakAd2, headers);
		ResponseEntity<PeakAdRequest> responseEntity = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity,
				PeakAdRequest.class);
		System.out.println("Status Code: " + responseEntity.getStatusCode());
		System.out.println("responseEntity.getBody(): " + responseEntity.getBody());
		return ((PeakAdRequest) responseEntity.getBody()).toString();
	}

	public static void main(String[] args) throws Exception {
		ClientController cc = new ClientController();
		String auth = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaHVhaWIyMDIyIiwiaWF0IjoxNjUwNTI5ODk3LCJleHAiOjE2NTA2MTYyOTd9.QfsSAKaW_F1gnsybmwuIGiVZabWStGQPwb8OPTJ1vct1zyGNT_V8Zdqcg0ApOnR4wL5rdX1Rz87ayrZ8wtGZGQ";
		String result = ClientController.getASingleSingleUserByAdmin("shuaib",auth);
		System.out.println(result);
		// String accessToken = "Bearer
		// eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlbmlvbGEiLCJpYXQiOjE2MzgzNDIyMTMsImV4cCI6MTYzODQyODYxM30.bFbm6n64w2oTaIelZ1jv7UYsbzi0GYFC7G7Kxr_VQfMKXzbW4PyWjWi2iK6E1Uw2fh8O-Pf2IoQ_HmuknE-auA";
		// cc.getFreeAdDetails("F2262", accessToken);
		// String result = cc.getFreeAdCartobePublished("1");
		// System.out.println("result: " + result);
	}
}