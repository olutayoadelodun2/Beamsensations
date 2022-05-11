package com.editay.bsps.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.editay.bsps.dto.PeakAdRequest;
import com.editay.bsps.models.FreeAd;
import com.editay.bsps.models.FreeAdCarDetails;
import com.editay.bsps.utility.Constants;

public class FreeAdClientController {

	static RestTemplate restTemplate = new RestTemplate();

	public String deleteFreeAdCarAndSubscription(long id, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", token);
		// HttpEntity<FreeAd> entity = new HttpEntity<FreeAd>(headers);
		HttpEntity<FreeAd> entity = new HttpEntity("parameters", headers);
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		return restTemplate
				.exchange(Constants.PROD_BASE_URL + "freeAds/" + id, HttpMethod.DELETE, entity, String.class)
				.getBody();
	}

	public String deleteFreeAdCarAndSubscription2(long id, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", token);
		// HttpEntity<FreeAd> entity = new HttpEntity<FreeAd>(headers);
		HttpEntity<FreeAd> entity = new HttpEntity("parameters", headers);
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		return restTemplate
				.exchange(Constants.PROD_BASE_URL + "freeAds/users/" + id, HttpMethod.DELETE, entity, String.class)
				.getBody();
	}
	
	public String deleteFreeAdCarAndSubscription3(long id, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", token);
		// HttpEntity<FreeAd> entity = new HttpEntity<FreeAd>(headers);
		HttpEntity<FreeAdCarDetails> entity = new HttpEntity("parameters", headers);
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		return restTemplate
				.exchange(Constants.PROD_BASE_URL + "wallet/" + id, HttpMethod.DELETE, entity, String.class)
				.getBody();
	}

	public static String checkIsUsedByFreeAdCarDetails(int id, String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		String theUrl = Constants.PROD_BASE_URL + "freeAdsCarUpload/freeadUploadDetails/" + id;
		System.out.println("theUrl for freeAdCarDetails: " + theUrl);
		System.out.println("id for freeAdCarDetails: " + id);
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

	public static String checkIsUsedByFreeAdCarDetails2(int id, String accessToken) {

		String response = "";
		JSONObject obj = new JSONObject();

		try {

			JSONObject obj2 = new JSONObject(FreeAdClientController.checkIsUsedByFreeAdCarDetails(id, accessToken));

			String isUsed = obj2.getString("isUsed");

			if (isUsed.equalsIgnoreCase("Y")) {

				obj.put("isUsed", "Y");
				response = obj.toString();
			} else if (isUsed.equalsIgnoreCase("N")) {

				obj.put("isUsed", "N");
				response = obj.toString();
			}

		} catch (Exception ex) {

			obj.put("isUsed", ex.getMessage());
			response = obj.toString();

		}

		return response;

	}

	public static String userCarUploadDetails(long id, String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/peakAds/peakId?peakId=" +
		// peakId;
		String theUrl = Constants.PROD_BASE_URL + "freeAdsCarUpload/caruploads/" + id;
		System.out.println("theUrl: " + theUrl);
		System.out.println("id: " + id);
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

	public static long getFreeAdSubscriptionIdFromCarUploadDetails(long id, String token) {

		String result = FreeAdClientController.userCarUploadDetails(id, token);
		JSONObject obj = new JSONObject(result);
		String id2 = obj.getString("freeAdId");
		System.out.println("id2: " + id2);

		long id3 = Long.parseLong(id2);

		return id3;

	}

	public static String checkIsUsedByFreeAd(int id, String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/peakAds/peakId?peakId=" +
		// peakId;
		String theUrl = Constants.PROD_BASE_URL + "freeAds/" + id;
		System.out.println("theUrl: " + theUrl);
		System.out.println("id: " + id);
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

	public static String checkIsUsedByFreeAd2(int id, String accessToken) {

		String response = "";
		JSONObject obj = new JSONObject();

		try {

			JSONObject obj2 = new JSONObject(FreeAdClientController.checkIsUsedByFreeAd(id, accessToken));

			String isUsed = obj2.getString("isUsed");

			if (isUsed.equalsIgnoreCase("Y")) {

				obj.put("isUsed", "Y");
				response = obj.toString();
			} else if (isUsed.equalsIgnoreCase("N")) {

				obj.put("isUsed", "N");
				response = obj.toString();
			}

		} catch (Exception ex) {

			obj.put("isUsed", ex.getMessage());
			response = obj.toString();

		}

		return response;

	}

	public static String getApprovedOrRejectedFreeAdCarupload(String id, String accountStatus, String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String response2 = "";
		// String theUrl = "http://api.beamsensations.com/api/kyc/username?username=" +
		// username;
		String theUrl = Constants.PROD_BASE_URL + "freeAdsCarUpload/approveorreject/update/" + id + "/" + accountStatus;

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

	public static void main(String[] args) {

		FreeAdClientController fc = new FreeAdClientController();
		String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaHVhaWIyMDIyIiwiaWF0IjoxNjUyMDIzODYyLCJleHAiOjE2NTIxMTAyNjJ9.KZjkBMc81rSuEqw1ZfZUuU5I6_sOETSAZKLmI3uxLlXHWbGBKGcN2Fkz2O7AdA6CGe7OGJzeOJWgSof5bBw6Mg";
		// String delete = fc.deleteFreeAdCarAndSubscription(104,token);
		// System.out.println("delete: " + delete);
		//long id = FreeAdClientController.getFreeAdSubscriptionIdFromCarUploadDetails(51, token);
		//System.out.println("userCarUploadDetails id:  " + id);
		//Long id = 36L;
		
		//String delete = fc.deleteFreeAdCarAndSubscription2(id,token);
		//System.out.println("delete 1: " + delete);
		
		String delete2 = fc.deleteFreeAdCarAndSubscription3(56,token);
		System.out.println("delete : " + delete2);
	}

}
