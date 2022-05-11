package com.editay.bsps.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "freead_car_details")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class FreeAdCarDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	
	private String productTitle;
	
	private String bodyType;

	@Column(columnDefinition = "varchar(255) default 'UNAPPROVED'")
	String accountStatus;

	@Column(name = "is_used", columnDefinition = "varchar(2) default 'N'")
	String isUsed;

	@Column(columnDefinition = "varchar(400) default 'null'")
	private String freeAdId;

	@Column
	@Null
	private String username;

	@Column(name = "accesstoken", columnDefinition = "varchar(400) default 'null'")
	private String accessToken;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

	@Column(name = "subscription_date")
	// private LocalDateTime date;
	private Timestamp date;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "freeAdCarDetails")
	@JsonManagedReference
	private FreeAdCarUpload freeAdCarUpload;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getFreeAdId() {
		return freeAdId;
	}

	public void setFreeAdId(String freeAdId) {
		this.freeAdId = freeAdId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public FreeAdCarUpload getFreeAdCarUpload() {
		return freeAdCarUpload;
	}

	public void setFreeAdCarUpload(FreeAdCarUpload freeAdCarUpload) {
		this.freeAdCarUpload = freeAdCarUpload;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public FreeAdCarDetails(String description) {

		this.description = description;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	
	

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	@Override
	public String toString() {
		return "FreeAdCarDetails [id=" + id + ", engineType=" + engineType + ", fuelType=" + fuelType
				+ ", transmission=" + transmission + ", mileage=" + mileage + ", engineCapacity=" + engineCapacity
				+ ", customPapers=" + customPapers + ", exteriorColour=" + exteriorColour + ", interiorColour="
				+ interiorColour + ", driverType=" + driverType + ", diagnostics=" + diagnostics + ", airbag=" + airbag
				+ ", carPrice=" + carPrice + ", vin=" + vin + ", vehicleId=" + vehicleId + ", model=" + model
				+ ", make=" + make + ", description=" + description + ", productTitle=" + productTitle + ", bodyType="
				+ bodyType + ", accountStatus=" + accountStatus + ", isUsed=" + isUsed + ", freeAdId=" + freeAdId
				+ ", username=" + username + ", accessToken=" + accessToken + ", date=" + date + ", freeAdCarUpload="
				+ freeAdCarUpload + "]";
	}
	
	

	
}
