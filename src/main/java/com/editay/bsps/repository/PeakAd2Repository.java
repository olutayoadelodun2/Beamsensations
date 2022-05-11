package com.editay.bsps.repository;

import com.editay.bsps.models.PeakAd2;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeakAd2Repository extends JpaRepository<PeakAd2, Long> {
   List<PeakAd2> findByPeakDescription(String peakSubscription);
}
