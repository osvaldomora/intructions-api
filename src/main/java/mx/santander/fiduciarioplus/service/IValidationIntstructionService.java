package mx.santander.fiduciarioplus.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mx.santander.fiduciarioplus.dto.validateInstruction.DataValidationInstructionResDto;


public interface IValidationIntstructionService {

	public DataValidationInstructionResDto validate (List<MultipartFile> files);

	
}
