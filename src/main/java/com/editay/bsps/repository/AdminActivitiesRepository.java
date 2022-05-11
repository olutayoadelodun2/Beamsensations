package com.editay.bsps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.editay.bsps.models.AdminActivities;


public interface AdminActivitiesRepository extends JpaRepository<AdminActivities, Long> {
	
	List findByAdminActivitiesId(Long adminActivitiesId); 

}
