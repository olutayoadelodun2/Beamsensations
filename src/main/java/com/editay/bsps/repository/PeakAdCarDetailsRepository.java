package com.editay.bsps.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.editay.bsps.models.*;

@Repository
public interface PeakAdCarDetailsRepository extends JpaRepository<PeakAdCarDetails, Integer> {

	

}
