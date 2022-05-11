package com.editay.bsps.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "admin_activities")
public class AdminActivities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String deletedBy;

	@NotNull
	private int itemDeletedId;

	@Column(columnDefinition = "varchar(255) default ''")
	private String nameOfDeletedItem;

	@CreationTimestamp
	@Column(updatable = false)
	Timestamp dateDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public int getItemDeletedId() {
		return itemDeletedId;
	}

	public void setItemDeletedId(int itemDeletedId) {
		this.itemDeletedId = itemDeletedId;
	}

	public String getNameOfDeletedItem() {
		return nameOfDeletedItem;
	}

	public void setNameOfDeletedItem(String nameOfDeletedItem) {
		this.nameOfDeletedItem = nameOfDeletedItem;
	}

	public Timestamp getDateDeleted() {
		return dateDeleted;
	}

	public void setDateDeleted(Timestamp dateDeleted) {
		this.dateDeleted = dateDeleted;
	}

	@Override
	public String toString() {
		return "AdminActivities [id=" + id + ", deletedBy=" + deletedBy + ", itemDeletedId=" + itemDeletedId
				+ ", nameOfDeletedItem=" + nameOfDeletedItem + ", dateDeleted=" + dateDeleted + "]";
	}
	
	
	


}
