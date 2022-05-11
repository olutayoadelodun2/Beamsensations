package com.editay.bsps.payload.request;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CarRequest {
   private UUID id;
   private String make;
   private String model;
   private String description;
   private String fuel;
   private String image;
   private BigDecimal price;
   private String power;
   private Integer mileage;
   private String date;
   private String username;
   private String subscriptionType;

   public CarRequest() {
   }

   public CarRequest(UUID id, String make, String model, String description, String fuel, String image, BigDecimal price, String power, Integer mileage, String date, String username, String subscriptionType) {
      this.id = id;
      this.make = make;
      this.model = model;
      this.description = description;
      this.fuel = fuel;
      this.image = image;
      this.price = price;
      this.power = power;
      this.mileage = mileage;
      this.date = date;
      this.username = username;
      this.subscriptionType = subscriptionType;
   }

   public UUID getId() {
      return this.id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public String getMake() {
      return this.make;
   }

   public void setMake(String make) {
      this.make = make;
   }

   public String getModel() {
      return this.model;
   }

   public void setModel(String model) {
      this.model = model;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getFuel() {
      return this.fuel;
   }

   public void setFuel(String fuel) {
      this.fuel = fuel;
   }

   public String getImage() {
      return this.image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getPower() {
      return this.power;
   }

   public void setPower(String power) {
      this.power = power;
   }

   public Integer getMileage() {
      return this.mileage;
   }

   public void setMileage(Integer mileage) {
      this.mileage = mileage;
   }

   public String getDate() {
      return this.date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getSubscriptionType() {
      return this.subscriptionType;
   }

   public void setSubscriptionType(String subscriptionType) {
      this.subscriptionType = subscriptionType;
   }
}
