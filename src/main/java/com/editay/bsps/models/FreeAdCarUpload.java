package com.editay.bsps.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "freead_car_uploads")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class FreeAdCarUpload {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull
	private String carImage;
	
	@Null
	private String carImage1;
	
	@Null
	private String carImage2;
	
	@Null
	private String carImage3;
	
	@Null
	private String carImage4;
	
	@Null
	private String carImage5;
	
	@Null
	private String carImage6;
	
	@Null
	private String carImage7;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	@JsonBackReference
	private FreeAdCarDetails freeAdCarDetails;

	public  Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarImage() {
		return carImage;
	}

	public void setCarImage(String carImage) {
		this.carImage = carImage;
	}

	public FreeAdCarDetails getFreeAdCarDetails() {
		return freeAdCarDetails;
	}

	public void setFreeAdCarDetails(FreeAdCarDetails freeAdCarDetails) {
		this.freeAdCarDetails = freeAdCarDetails;
	}

	public String getCarImage1() {
		return carImage1;
	}

	public void setCarImage1(String carImage1) {
		this.carImage1 = carImage1;
	}

	public String getCarImage2() {
		return carImage2;
	}

	public void setCarImage2(String carImage2) {
		this.carImage2 = carImage2;
	}

	public String getCarImage3() {
		return carImage3;
	}

	public void setCarImage3(String carImage3) {
		this.carImage3 = carImage3;
	}

	public String getCarImage4() {
		return carImage4;
	}

	public void setCarImage4(String carImage4) {
		this.carImage4 = carImage4;
	}

	public String getCarImage5() {
		return carImage5;
	}

	public void setCarImage5(String carImage5) {
		this.carImage5 = carImage5;
	}

	public String getCarImage6() {
		return carImage6;
	}

	public void setCarImage6(String carImage6) {
		this.carImage6 = carImage6;
	}

	public String getCarImage7() {
		return carImage7;
	}

	public void setCarImage7(String carImage7) {
		this.carImage7 = carImage7;
	}

	@Override
	public String toString() {
		return "FreeAdCarUpload [id=" + id + ", carImage=" + carImage + ", carImage1=" + carImage1 + ", carImage2="
				+ carImage2 + ", carImage3=" + carImage3 + ", carImage4=" + carImage4 + ", carImage5=" + carImage5
				+ ", carImage6=" + carImage6 + ", carImage7=" + carImage7 + ", freeAdCarDetails=" + freeAdCarDetails
				+ "]";
	}
	
	
	
	
	

}
