package com.editay.bsps.utility;

public class Generator {
	
	   public static String getPaymentReference() {
	      String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
	      StringBuilder sb = new StringBuilder(19);

	      for(int i = 0; i < 19; ++i) {
	         int index = (int)((double)AlphaNumericString.length() * Math.random());
	         sb.append(AlphaNumericString.charAt(index));
	      }

	      return sb.toString();
	   }
}
