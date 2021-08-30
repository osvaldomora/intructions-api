package mx.santander.fiduciarioplus.controller;

import java.util.List;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.santander.fiduciarioplus.dto.countstatesinstructions.CountInstructionsResDto;
import mx.santander.fiduciarioplus.dto.listinstructions.InstructionsResDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.response.DataSendInstructionResDto;
import mx.santander.fiduciarioplus.dto.typeinstruction.DataTypeInstructionResDto;
import mx.santander.fiduciarioplus.service.IInstructionService;
import mx.santander.fiduciarioplus.service.ITypeInstructionService;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST}, allowedHeaders = "*")
@RequestMapping("/api/instructions/v1")
public class InstructionController {

	@Autowired
	ITypeInstructionService typeInstructionService;
	
	@Autowired
	IInstructionService instructionService;

	@GetMapping(value = "/type_instructions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listTypeInstructions() {
		DataTypeInstructionResDto dataTypeInstructionResDto = typeInstructionService.getInstructions();
		return ResponseEntity.status(HttpStatus.OK).body(dataTypeInstructionResDto);
	}
	
	@PostMapping(value = "/instructions", 
				produces = MediaType.APPLICATION_JSON_VALUE, 
				consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> saveInstructions(@RequestParam(name = "files", required = true) List<MultipartFile> files, 
											  @RequestParam(name = "instruction", required = true) String instruction){
		DataSendInstructionResDto dataSendInstructionResDto = instructionService.save(instruction, files);
		if(dataSendInstructionResDto == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(dataSendInstructionResDto);
	}
	
	@GetMapping(value = "/instructions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listInstructions(@RequestParam(name = "buc", required = true)String buc,
											  @RequestParam(name = "business.id", required = true)Integer businessId,
											  @RequestParam(name = "subBusiness.id", required = true)Integer subBusinessId) throws ParseException{
		InstructionsResDto instructionsResDto = instructionService.getListInstructions(buc,businessId,subBusinessId);
		if(instructionsResDto.getData().getInstructions().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(instructionsResDto);
	}
	
	
	@GetMapping(value = "/instructions/count_status", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> countStatusInstructions(@RequestParam(name = "buc", required = true)String buc,
			  										 @RequestParam(name = "business.id", required = false)Integer businessId,
			  										@RequestParam(name = "subBusiness.id", required = false)Integer subBusinessId){
		CountInstructionsResDto countInstructionsResDto = instructionService.getListCountInstructions(buc,businessId,subBusinessId);
		
		
		if(countInstructionsResDto.getData().getStatus().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(countInstructionsResDto);
	}
	
	/*Errores 
	 * MissingServletRequestParameterException
	 * MissingServletRequestPartException
	 * HttpMediaTypeNotSupportedException
	 * MaxUploadSizeExceededException
	 */
	
}
