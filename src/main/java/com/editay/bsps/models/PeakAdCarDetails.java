package com.editay.bsps.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Table(name = "peakad_car_details")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PeakAdCarDetails {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	

	@NotNull
	private String engineType;

	@NotNull
	private String fuelType;

	@NotNull
	private String transmission;

	@NotNull
	private String mileage;

	@NotNull
	private String engineCapacity;

	private String customPapers;

	@NotNull
	private String exteriorColour;

	@NotNull
	private String interiorColour;

	@NotNull
	private String driverType;

	private String diagnostics;

	private String airbag;

	@NotNull
	private String carPrice;

	@NotNull
	private String vin;

	@NotNull
	private String vehicleId;

	@NotNull
	private String model;

	@NotNull
	private String make;

	private String description;

	@NotNull
	private String peakAdId;

	@NotNull
	@JsonIgnore
	private String username;
	
	
	@Column(name = "accesstoken")
	private String accessToken;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

	@Column(name = "subscription_date")
	private LocalDateTime date;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "peakad_caruploads", joinColumns = { @JoinColumn(name = "peakad_car_details_id") }, inverseJoinColumns = {
            @JoinColumn(name = "peakad_car_uploads_id") })
	private Set<PeakAdCarUpload> peakAdCarUpload;
	
	public String getCustomPapers() {
		return customPapers;
	}

	public void setCustomPapers(String customPapers) {
		this.customPapers = customPapers;
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

	public  int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
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

	
    
	public Set<PeakAdCarUpload> getPeakAdCarUpload() {
		return peakAdCarUpload;
	}

	public void setPeakAdCarUpload(Set<PeakAdCarUpload> peakAdCarUpload) {
		this.peakAdCarUpload = peakAdCarUpload;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	public void addPeakAdCarUpload(PeakAdCarUpload peakAdCarUpload) {
		this.peakAdCarUpload.add(peakAdCarUpload);
        peakAdCarUpload.getPeakAdCarDetails().add(this);
    }
    public void removePeakAdCarUpload(PeakAdCarUpload peakAdCarUpload) {
        this.getPeakAdCarUpload().remove(peakAdCarUpload);
        peakAdCarUpload.getPeakAdCarDetails().remove(this);
      
    }
    public void removePeakAdCarUploads() {
    	for(PeakAdCarUpload peakAdCarUpload : new HashSet<>(peakAdCarUpload)) {
    		
    		removePeakAdCarUpload(peakAdCarUpload);
    	}
        
    }
	
	
	
	
	
	

}
