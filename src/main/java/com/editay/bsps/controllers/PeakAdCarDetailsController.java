package com.editay.bsps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.editay.bsps.dto.PeakAdCarUploadDto;
import com.editay.bsps.repository.UserRepository;
import com.editay.bsps.service.PeakAdCarDetailsService;

@CrossOrigin(origins = { "*" }, maxAge = 3600L)
@RestController
@RequestMapping({ "/api/peakAdsCarUpload" })
public class PeakAdCarDetailsController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private PeakAdCarDetailsService peakAdCarDetailsService;

	@GetMapping("/carUploads")
	public ResponseEntity<List<PeakAdCarUploadDto>> getAllPeakAdCarDetails() {
		List<PeakAdCarUploadDto> peakAdCarUploads = peakAdCarDetailsService.getAllPeakAdCarUploads();
		return new ResponseEntity<>(peakAdCarUploads, HttpStatus.OK);
	}

	@PostMapping("/carUpload")
	public ResponseEntity<PeakAdCarUploadDto> getAllPeakAdCarDetails(
			@RequestBody PeakAdCarUploadDto peakAdCarUploadDto) {
		PeakAdCarUploadDto pcu = peakAdCarDetailsService.addStudent(peakAdCarUploadDto);
		return new ResponseEntity<>(pcu, HttpStatus.CREATED);
	}

	@PutMapping("/carUpload/{id}")
	public ResponseEntity<PeakAdCarUploadDto> updatePeakAdCarDetails(@PathVariable(name = "id") Integer id,
			@RequestBody PeakAdCarUploadDto peakAdCarUpload) {
		PeakAdCarUploadDto pcu = peakAdCarDetailsService.updatePeakAdCarUpload(id, peakAdCarUpload);
		return new ResponseEntity<>(pcu, HttpStatus.CREATED);
	}

	@DeleteMapping("/carUpload/{id}")
	public ResponseEntity<String> deletePeakAdCarDetails(@PathVariable(name = "id") Integer peakAdCarDetailsId) {
		String message = peakAdCarDetailsService.deletePeakAdCarUpload(peakAdCarDetailsId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
