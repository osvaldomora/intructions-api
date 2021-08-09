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

import mx.santander.fiduciarioplus.dto.tipoinstruccion.InstruccionDto;
import mx.santander.fiduciarioplus.service.IInstruccionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/instrucciones/v1")
public class InstruccionController {

	@Autowired
	IInstruccionService instruccionService;

	@GetMapping(value = "/tipo_instrucciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarTipoInstrucciones() {
		InstruccionDto listInstruccion= instruccionService.getInstrucciones();
//		listInstruccion.stream().forEach(instruction->System.out.println(instruction));
		return ResponseEntity.status(HttpStatus.OK).body(listInstruccion);
	}

}
