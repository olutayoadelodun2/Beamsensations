package com.editay.bsps.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class CommonUtil {
   public static String peakId(long days, long peak) {
      Random random = new Random();
      int rand = random.nextInt(1000);
      String result = peak + "P" + days + "D" + rand;
      return result;
   }

   public static String freeId(long days) {
      Random random = new Random();
      int rand = random.nextInt(1000);
      String result = "F" + days + rand;
      return result;
   }

   public static String hoistId(long months, String hoistType) {
      Random random = new Random();
      int rand = random.nextInt(1000);
      String result = months + "m" + hoistType + rand;
      return result.replace(" ", "");
   }

   public static String expireDate(String date2, int dy) {
      String year = date2.substring(0, 4);
      System.out.println(year);
      String months = date2.substring(5, 7);
      System.out.println(months);
      String days = date2.substring(8, 10);
      System.out.println(days);
      LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(months), Integer.parseInt(days));
      LocalDate newDate = localDate.plusDays((long)dy);
      return newDate.toString() + " 00:00:00";
   }

   public static String expireDate2(String date2, int month) {
      String year = date2.substring(0, 4);
      System.out.println(year);
      String months = date2.substring(5, 7);
      System.out.println(months);
      String days = date2.substring(8, 10);
      System.out.println(days);
      LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(months), Integer.parseInt(days));
      LocalDate newDate = null;
      if (month == 1) {
         newDate = localDate.plusDays(31L);
      } else if (month == 2) {
         newDate = localDate.plusDays(62L);
      } else if (month == 3) {
         newDate = localDate.plusDays(93L);
      } else if (month == 4) {
         newDate = localDate.plusDays(124L);
      } else if (month == 5) {
         newDate = localDate.plusDays(155L);
      } else if (month == 6) {
         newDate = localDate.plusDays(186L);
      } else if (month == 7) {
         newDate = localDate.plusDays(217L);
      } else if (month == 8) {
         newDate = localDate.plusDays(248L);
      } else if (month == 9) {
         newDate = localDate.plusDays(279L);
      } else if (month == 10) {
         newDate = localDate.plusDays(310L);
      } else if (month == 11) {
         newDate = localDate.plusDays(341L);
      } else if (month == 12) {
         newDate = localDate.plusDays(372L);
      }

      return newDate.toString() + " 00:00:00";
   }

   public static String todayDate() {
      SimpleDateFormat dnt = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
      Date date = new Date();
      return dnt.format(date);
   }
   
   public static String convertToDate(String timestamp) {
	   
	  // String date = "2021-11-30T10:34:34.000+00:00";
       String date2 = timestamp.substring(0, 19);
       System.out.println("date2: " + date2);
       String date3 = date2.replace("T", " ");
       System.out.println("date3: " + date3);
       
       return date3;
   }
}
