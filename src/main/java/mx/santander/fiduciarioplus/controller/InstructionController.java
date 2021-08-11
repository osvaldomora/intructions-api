package mx.santander.fiduciarioplus.controller;

import java.util.List;

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

import mx.santander.fiduciarioplus.dto.sendinstruction.response.DataSendInstructionResDto;
import mx.santander.fiduciarioplus.dto.typeinstruction.DataTypeInstructionResDto;
import mx.santander.fiduciarioplus.service.IInstructionService;
import mx.santander.fiduciarioplus.service.ITypeInstructionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/instructions/v1")
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
	
	@PostMapping(value = "/instructions", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> saveInstructions(@RequestParam("files") List<MultipartFile> files, String instruction){
		DataSendInstructionResDto dataSendInstructionResDto = instructionService.save(instruction, files);
		if(dataSendInstructionResDto == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(dataSendInstructionResDto);
	}

}
