package mx.santander.fiduciarioplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.santander.fiduciarioplus.dto.typeinstruction.DataDto;
import mx.santander.fiduciarioplus.service.ITypeInstructionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/instructions/v1")
public class InstructionController {

	@Autowired
	ITypeInstructionService instructionService;

	@GetMapping(value = "/type_instructions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listTypeInstructions() {
		DataDto listInstruction= instructionService.getInstructions();
//		listInstruccion.stream().forEach(instruction->System.out.println(instruction));
		return ResponseEntity.status(HttpStatus.OK).body(listInstruction);
	}

}
