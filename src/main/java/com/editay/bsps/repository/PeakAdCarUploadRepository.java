package com.editay.bsps.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.editay.bsps.models.PeakAdCarDetails;
import com.editay.bsps.models.PeakAdCarUpload;



@Repository
public interface PeakAdCarUploadRepository extends JpaRepository<PeakAdCarUpload, Integer> {
	
	
	
	 @Query(value = "SELECT * from peakad_car_uploads where car_image = ?1", nativeQuery = true)
	public PeakAdCarUpload findByName(String imageName);
}
