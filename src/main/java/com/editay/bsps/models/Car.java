package com.editay.bsps.models;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
   name = "car"
)
public class Car {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   @Column(
      name = "subscription_id"
   )
   private UUID id;
   @Column(
      name = "make"
   )
   private String make;
   @Column(
      name = "model"
   )
   private String model;
   @Column(
      name = "description"
   )
   private String description;
   @Column(
      name = "fuel"
   )
   private String fuel;
   @Column(
      name = "image"
   )
   private String image;
   @Column(
      name = "price"
   )
   private BigDecimal price;
   @Column(
      name = "power"
   )
   private String power;
   @Column(
      name = "mileage"
   )
   private Integer mileage;
   @Column(
      name = "date"
   )
   private String date;
   @Column(
      name = "username"
   )
   @JsonIgnore
   private String username;
   @Column(
      name = "subscription_type"
   )
   private String subscriptionType;

   public Car(String make, String model, String description, String fuel, String image, Integer price, String power, Integer mileage, String date, String username) {
   }

   public Car(UUID id, String make, String model, String description, String fuel, String image, BigDecimal price, String power, Integer mileage, String date, String username) {
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
   }

   public Car() {
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
