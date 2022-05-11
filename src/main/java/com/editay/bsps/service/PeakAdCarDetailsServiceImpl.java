package com.editay.bsps.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.editay.bsps.dto.PeakAdCarUploadDto;
import com.editay.bsps.models.PeakAdCarDetails;
import com.editay.bsps.models.PeakAdCarUpload;
import com.editay.bsps.repository.PeakAdCarDetailsRepository;
import com.editay.bsps.repository.PeakAdCarUploadRepository;


@Service
public class PeakAdCarDetailsServiceImpl implements PeakAdCarDetailsService {

	@Resource
	private PeakAdCarDetailsRepository peakAdCarDetailsRepository;
	@Resource
	private PeakAdCarUploadRepository peakAdCarUploadRepository;

	@Transactional
	@Override
	public PeakAdCarUploadDto addStudent(PeakAdCarUploadDto peakAdCarUploadDto) {
		// TODO Auto-generated method stub
		PeakAdCarDetails peakAdCarDetails = new PeakAdCarDetails();
		mapDtoToEntity(peakAdCarUploadDto, peakAdCarDetails);
		PeakAdCarDetails savedPeakAdCarDetails = peakAdCarDetailsRepository.save(peakAdCarDetails);
		return mapEntityToDto(savedPeakAdCarDetails);
	}

	@Override
	public List<PeakAdCarUploadDto> getAllPeakAdCarUploads() {
		// TODO Auto-generated method stub
		List<PeakAdCarUploadDto> peakAdCarUploadDtos = new ArrayList<>();
		List<PeakAdCarDetails> peakAdCarDetails = peakAdCarDetailsRepository.findAll();
		peakAdCarDetails.stream().forEach(peakAdCarDetail -> {
			PeakAdCarUploadDto peakAdCarUploadDto = mapEntityToDto(peakAdCarDetail);
			peakAdCarUploadDtos.add(peakAdCarUploadDto);
		});
		return peakAdCarUploadDtos;
	}

	@Override
	public PeakAdCarUploadDto updatePeakAdCarUpload(Integer peakAdCarUploadId, PeakAdCarUploadDto peakAdCarUploadDto) {
		// TODO Auto-generated method stub
		PeakAdCarDetails peakAdCarDetails = peakAdCarDetailsRepository.getOne(peakAdCarUploadId);
		peakAdCarDetails.getPeakAdCarUpload().clear();
		mapDtoToEntity(peakAdCarUploadDto, peakAdCarDetails);
		PeakAdCarDetails peakAdCarDetailss = peakAdCarDetailsRepository.save(peakAdCarDetails);
		return mapEntityToDto(peakAdCarDetailss);
	}

	@Override
	public String deletePeakAdCarUpload(Integer peakAdCarUploadId) {
		// TODO Auto-generated method stub
		Optional<PeakAdCarDetails> peakAdCarDetails = peakAdCarDetailsRepository.findById(peakAdCarUploadId);
		// Remove the related courses from student entity.
		if (peakAdCarDetails.isPresent()) {
			peakAdCarDetails.get().removePeakAdCarUploads();
			;
			peakAdCarDetailsRepository.deleteById(peakAdCarDetails.get().getId());
			return "Student with id: " + peakAdCarUploadId + " deleted successfully!";
		}
		return null;
	}

	private void mapDtoToEntity(PeakAdCarUploadDto peakAdCarUploadDto, PeakAdCarDetails peakAdCarDetails) {
		peakAdCarDetails.setAccessToken(peakAdCarUploadDto.getAccessToken());
		peakAdCarDetails.setAirbag(peakAdCarUploadDto.getAirbag());
		peakAdCarDetails.setCarPrice(peakAdCarUploadDto.getCarPrice());
		peakAdCarDetails.setCustomPapers(peakAdCarUploadDto.getCustomPapers());
		peakAdCarDetails.setDate(peakAdCarUploadDto.getDate());
		peakAdCarDetails.setDescription(peakAdCarUploadDto.getDescription());
		peakAdCarDetails.setDiagnostics(peakAdCarUploadDto.getDiagnostics());
		peakAdCarDetails.setDriverType(peakAdCarUploadDto.getDriverType());
		peakAdCarDetails.setEngineCapacity(peakAdCarUploadDto.getEngineCapacity());
		peakAdCarDetails.setEngineType(peakAdCarUploadDto.getEngineType());
		peakAdCarDetails.setExteriorColour(peakAdCarUploadDto.getExteriorColour());
		peakAdCarDetails.setFuelType(peakAdCarUploadDto.getFuelType());
		peakAdCarDetails.setInteriorColour(peakAdCarUploadDto.getInteriorColour());
		peakAdCarDetails.setMake(peakAdCarUploadDto.getMake());
		peakAdCarDetails.setMileage(peakAdCarUploadDto.getMileage());
		peakAdCarDetails.setModel(peakAdCarUploadDto.getModel());
		peakAdCarDetails.setPeakAdId(peakAdCarUploadDto.getPeakAdId());
		peakAdCarDetails.setTransmission(peakAdCarUploadDto.getTransmission());
		peakAdCarDetails.setUsername(peakAdCarUploadDto.getUsername());
		peakAdCarDetails.setVehicleId(peakAdCarUploadDto.getVehicleId());
		peakAdCarDetails.setVin(peakAdCarUploadDto.getVin());
		if (null == peakAdCarDetails.getPeakAdCarUpload()) {
			peakAdCarDetails.setPeakAdCarUpload(new HashSet<>());
		}
		peakAdCarUploadDto.getPeakAdCarUploads().stream().forEach(imageURL -> {
			PeakAdCarUpload peakAdCarUpload = peakAdCarUploadRepository.findByName(imageURL);
			if (null == peakAdCarUpload) {
				peakAdCarUpload = new PeakAdCarUpload();
				peakAdCarUpload.setPeakAdCarDetails(new HashSet<>());
			}

			peakAdCarUpload.setCarImage(imageURL);
			peakAdCarDetails.addPeakAdCarUpload(peakAdCarUpload);

		});
	}

	private PeakAdCarUploadDto mapEntityToDto(PeakAdCarDetails peakAdCarDetails) {
		PeakAdCarUploadDto responseDto = new PeakAdCarUploadDto();

		responseDto.setAccessToken(peakAdCarDetails.getAccessToken());
		responseDto.setAirbag(peakAdCarDetails.getAirbag());
		responseDto.setCarPrice(peakAdCarDetails.getCarPrice());
		responseDto.setCustomPapers(peakAdCarDetails.getCustomPapers());
		responseDto.setDate(peakAdCarDetails.getDate());
		responseDto.setDescription(peakAdCarDetails.getDescription());
		responseDto.setDiagnostics(peakAdCarDetails.getDiagnostics());
		responseDto.setDriverType(peakAdCarDetails.getDriverType());
		responseDto.setEngineCapacity(peakAdCarDetails.getEngineCapacity());
		responseDto.setEngineType(peakAdCarDetails.getEngineType());
		responseDto.setExteriorColour(peakAdCarDetails.getExteriorColour());
		responseDto.setFuelType(peakAdCarDetails.getFuelType());
		responseDto.setInteriorColour(peakAdCarDetails.getInteriorColour());
		responseDto.setMake(peakAdCarDetails.getMake());
		responseDto.setMileage(peakAdCarDetails.getMileage());
		responseDto.setModel(peakAdCarDetails.getModel());
		responseDto.setPeakAdId(peakAdCarDetails.getPeakAdId());
		responseDto.setTransmission(peakAdCarDetails.getTransmission());
		responseDto.setUsername(peakAdCarDetails.getUsername());
		responseDto.setVehicleId(peakAdCarDetails.getVehicleId());
		responseDto.setVin(peakAdCarDetails.getVin());
		responseDto.setPeakAdCarUploads(peakAdCarDetails.getPeakAdCarUpload().stream().map(PeakAdCarUpload::getCarImage)
				.collect(Collectors.toSet()));

		return responseDto;
	}

}
