package com.editay.bsps.service;

import java.util.List;
import com.editay.bsps.dto.PeakAdCarUploadDto;


public interface PeakAdCarDetailsService {
	
	
	public PeakAdCarUploadDto addStudent(PeakAdCarUploadDto peakAdCarUploadDto);
    public List<PeakAdCarUploadDto> getAllPeakAdCarUploads();
    public PeakAdCarUploadDto updatePeakAdCarUpload(Integer peakAdCarUploadId, PeakAdCarUploadDto peakAdCarUploadDto);
    public String deletePeakAdCarUpload(Integer peakAdCarUploadId);

}
