package com.editay.bsps.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "hoistAds")

@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HoistAd {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "hoist_description", nullable = false, columnDefinition = "TEXT")
	private String hoistDescription;
	@Column(name = "no_of_months", nullable = false)
	private long noOfMonths;
	@Column(name = "hoist_type", nullable = false)
	private String hoistType;
	@Column(name = "amount", nullable = false)
	private BigDecimal amount;
	@Column
	@JsonIgnore
	String username;
	@Column(columnDefinition = "varchar(255) default 'UNAPPROVED'")
	String accountStatus;
	@Column(columnDefinition = "varchar(255) default '₦ 0'")
	String balance;
	@Column(name = "hoist_id")
	private String hoistId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "subscription_date")
	private LocalDateTime subscriptionDate;
	@Column(name = "subscription_expire")
	private String subscriptionExpireDate;
	@CreationTimestamp
	@Column(updatable = false)
	Timestamp dateCreated;
	@UpdateTimestamp
	Timestamp lastModified;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHoistDescription() {
		return hoistDescription;
	}

	public void setHoistDescription(String hoistDescription) {
		this.hoistDescription = hoistDescription;
	}

	public long getNoOfMonths() {
		return noOfMonths;
	}

	public void setNoOfMonths(long noOfMonths) {
		this.noOfMonths = noOfMonths;
	}

	public String getHoistType() {
		return hoistType;
	}

	public void setHoistType(String hoistType) {
		this.hoistType = hoistType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getHoistId() {
		return hoistId;
	}

	public void setHoistId(String hoistId) {
		this.hoistId = hoistId;
	}

	public LocalDateTime getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(LocalDateTime subscriptionDate) {
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

}
