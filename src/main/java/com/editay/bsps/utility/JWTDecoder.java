package com.editay.bsps.utility;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

public class JWTDecoder {

	public static String token(String jwtToken) {

		//String jwtToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlbmlvbGEiLCJpYXQiOjE2MzgzNDIyMTMsImV4cCI6MTYzODQyODYxM30.bFbm6n64w2oTaIelZ1jv7UYsbzi0GYFC7G7Kxr_VQfMKXzbW4PyWjWi2iK6E1Uw2fh8O-Pf2IoQ_HmuknE-auA";
		System.out.println("------------ Decode JWT ------------");
		String jwtTokenRemoveBearer = jwtToken.replace("Bearer", "").replace(" ", "");
		System.out.println("real token: " + jwtTokenRemoveBearer);
		String[] split_string = jwtToken.split("\\.");
		String base64EncodedHeader = split_string[0];
		String base64EncodedBody = split_string[1];
		String base64EncodedSignature = split_string[2];

		System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
		Base64 base64Url = new Base64(true);
		String header = new String(base64Url.decode(base64EncodedHeader));
		System.out.println("JWT Header : " + header);

		System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
		String body = new String(base64Url.decode(base64EncodedBody));
		System.out.println("JWT Body : " + body);
		
		return body;
	}
	
	public static String findTokenUsername(String token) {
		
		JSONObject obj = new JSONObject(token);
        String username = obj.getString("sub");
        System.out.println("username: " + username);
        
        return username;
	}

}
