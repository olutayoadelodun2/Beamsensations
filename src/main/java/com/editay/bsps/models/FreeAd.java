package com.editay.bsps.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Null;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "freeAds")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FreeAd {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	//Integer id;
	@Column(name = "freead_description", nullable = false, columnDefinition = "VARCHAR(255) default 'FREE AD'")
	private String freeDescription;
	@Column(name = "no_of_days", nullable = false,columnDefinition = "BIGINT(20) default 2")
	private long noOfDays;
	@Column
	@Null
	String username;
	@Column(columnDefinition = "varchar(255) default 'UNAPPROVED'")
	String accountStatus;
	@Column(name = "is_used",columnDefinition = "varchar(2) default 'N'")
	String isUsed;
	@Column(name = "freead_id")
	private String freeAdId;
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "subscription_date")
	@CreationTimestamp
	//private LocalDateTime subscriptionDate;
	private Timestamp subscriptionDate;
	@Column(name = "subscription_expire")
	private String subscriptionExpireDate;
	@CreationTimestamp
	@Column(updatable = false)
	Timestamp dateCreated;
	@UpdateTimestamp
	Timestamp lastModified;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFreeDescription() {
		return freeDescription;
	}

	public void setFreeDescription(String freeDescription) {
		this.freeDescription = freeDescription;
	}

	public long getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(long noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getFreeAdId() {
		return freeAdId;
	}

	public void setFreeAdId(String freeAdId) {
		this.freeAdId = freeAdId;
	}

	public Timestamp getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(Timestamp subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public String getSubscriptionExpireDate() {
		return subscriptionExpireDate;
	}

	public void setSubscriptionExpireDate(String subscriptionExpireDate) {
		this.subscriptionExpireDate = subscriptionExpireDate;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	
	
	
	
	
	

}
