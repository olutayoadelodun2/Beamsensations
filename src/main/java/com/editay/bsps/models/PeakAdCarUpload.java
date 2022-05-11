package com.editay.bsps.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "peakad_car_uploads")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PeakAdCarUpload {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@NotNull
	private String carImage;
	
	
	
	@ManyToMany(mappedBy = "peakAdCarUpload")
    @JsonIgnore
    private Set<PeakAdCarDetails> peakAdCarDetails;


	public  int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCarImage() {
		return carImage;
	}

	public void setCarImage(String carImage) {
		this.carImage = carImage;
	}

	public Set<PeakAdCarDetails> getPeakAdCarDetails() {
		return peakAdCarDetails;
	}

	public void setPeakAdCarDetails(Set<PeakAdCarDetails> peakAdCarDetails) {
		this.peakAdCarDetails = peakAdCarDetails;
	}

	
	
	
	
	
}
