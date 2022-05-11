package com.editay.bsps.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;



public class PeakAdCarUploadDto {
	
	
	private String engineType;

	
	private String fuelType;

	
	private String transmission;

	
	private String mileage;

	
	private String engineCapacity;

	private String customPapers;

	
	private String exteriorColour;

	
	private String interiorColour;

	
	private String driverType;

	private String diagnostics;

	private String airbag;

	
	private String carPrice;

	
	private String vin;

	
	private String vehicleId;

	
	private String model;

	
	private String make;

	private String description;

	
	private String peakAdId;

	
	private String username;
	
	
	private String accessToken;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;
	
	private Set<String> peakAdCarUploads = new HashSet<>();
	
	
	

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(String engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public String getCustomPapers() {
		return customPapers;
	}

	public void setCustomPapers(String customPapers) {
		this.customPapers = customPapers;
	}

	public String getExteriorColour() {
		return exteriorColour;
	}

	public void setExteriorColour(String exteriorColour) {
		this.exteriorColour = exteriorColour;
	}

	public String getInteriorColour() {
		return interiorColour;
	}

	public void setInteriorColour(String interiorColour) {
		this.interiorColour = interiorColour;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	public String getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(String diagnostics) {
		this.diagnostics = diagnostics;
	}

	public String getAirbag() {
		return airbag;
	}

	public void setAirbag(String airbag) {
		this.airbag = airbag;
	}

	public String getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(String carPrice) {
		this.carPrice = carPrice;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPeakAdId() {
		return peakAdId;
	}

	public void setPeakAdId(String peakAdId) {
		this.peakAdId = peakAdId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Set<String> getPeakAdCarUploads() {
		return peakAdCarUploads;
	}

	public void setPeakAdCarUploads(Set<String> peakAdCarUploads) {
		this.peakAdCarUploads = peakAdCarUploads;
	}
	
	
	
	

}
