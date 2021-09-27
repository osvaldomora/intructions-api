package mx.santander.fiduciarioplus.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import mx.santander.fiduciarioplus.dto.instruction.count.CountInstructionsResDto;
import mx.santander.fiduciarioplus.dto.instruction.list.InstructionsResDto;
import mx.santander.fiduciarioplus.dto.typeInstruction.download.TypeInstrFileDownloadDto;
import mx.santander.fiduciarioplus.dto.typeInstruction.list.TypeInstructionsDataResDto;
import mx.santander.fiduciarioplus.service.IInstructionSentService;
import mx.santander.fiduciarioplus.service.ITypeInstructionService;


/**
 * Este controlador permite realizar diferentes operaciones HTTP, asociado al recurso intructions
 * Se cuenta con los service: 
 * 1.- typeInstruction: Servicio encargado de manejar recurso de tipo de instrucciones
 *
 */
@Setter
@RestController
@RequestMapping("/instructions/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST}, allowedHeaders = "*")
public class InstructionController {

	//La Constante LOGGER. Obtiene el Logger de la clase
	private static final Logger LOGGER = LoggerFactory.getLogger(InstructionController.class);
	
	//Variable de servicio de Tipo de Instruccion
	@Autowired
	private ITypeInstructionService typeInstructionService;
	
	//Variable de servicio de instrucciones
	@Autowired
	private IInstructionSentService instructionSendService;
	
	
	@GetMapping("/instructions/count_status")
	public ResponseEntity<?> countInstructions(@RequestParam(name = "buc", required = true)String buc,
			  									@RequestParam(name = "business.id", required = false)Long businessId,
			  									@RequestParam(name = "subBusiness.id", required = false)Long subBusinessId){
		
		LOGGER.info("Metodo: GET, Operacion: findAllInstructions, buc: {}, businnes: {}, subBusiness: {}",buc,businessId,subBusinessId);
		CountInstructionsResDto countInstructionsResDto = this.instructionSendService.countInstructions(buc, businessId, subBusinessId);
		
		if(countInstructionsResDto.getData().getStatusPerDay().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(countInstructionsResDto);
	}
	
	

	/**
     * Este es un metodo HTTP GET consulta el recurso de instrucciones
	 * y en la implementacion de la interfaz de negocio instructionSendServicee
	 * puede realizar ciertas transformaciones DTO a la consulta para enriquecer la presentacion.
	 * 
	 * Este metodo es idempotente, y sus procesos derivados NUNCA deben modificar el estado de algun recurso en el servidor. 
	 * TODOS los procesos desencadenados deben ser solo de consulta.
	 * 
	 * @param buc identificador unico del cliente
	 * @param businessId contrato de cliente
	 * @param subBusinessId subContrato de cliente
	 * @return lista de clientes asociadas al cliente
	 */
	@GetMapping("/instructions")
	public ResponseEntity<?> findAllInstructions(@RequestParam(name = "buc", required = true)String buc,
			  									@RequestParam(name = "business.id", required = true)Long businessId,
			  									@RequestParam(name = "subBusiness.id", required = true)Long subBusinessId){
		
		LOGGER.info("Metodo: GET, Operacion: findAllInstructions, buc: {}, businnes: {}, subBusiness: {}",buc,businessId,subBusinessId);
		InstructionsResDto instructionsResDto = this.instructionSendService.findAll(buc, businessId, subBusinessId);
		
		if(instructionsResDto.getData().getInstructions().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(instructionsResDto);
	}
	
    /**
     * Este es un metodo HTTP GET consulta el recurso de tipo de instruccion
	 * y en la implementacion de la interfaz de negocio typeInstructionService
	 * puede realizar ciertas transformaciones DTO a la consulta para enriquecer la presentacion.
	 * 
	 * Este metodo es idempotente, y sus procesos derivados NUNCA deben modificar el estado de algun recurso en el servidor. 
	 * TODOS los procesos desencadenados deben ser solo de consulta.
	 * 
     * @return Una lista de typeInstructions en un objeto JSON obtenido
     */
	@GetMapping("/type_instructions")
	public ResponseEntity<?> findAllTypeInstructions(){
		LOGGER.info("Metodo: GET, Operacion: findAllTypeInstructions");
		TypeInstructionsDataResDto resDto = typeInstructionService.findAllListTypInstr();
		if(resDto.getData().getTypeInstructions().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(resDto);
	}
	
	/**
     * Este es un metodo HTTP GET descarga la plantilla de un tipo de instruccion
	 * y en la implementacion de la interfaz de negocio typeInstructionService
	 * puede realizar ciertas transformaciones DTO a la consulta para enriquecer la presentacion.
	 * 
	 * Este metodo es idempotente, y sus procesos derivados NUNCA deben modificar el estado de algun recurso en el servidor. 
	 * TODOS los procesos desencadenados deben ser solo de consulta.
	 * @param id Identificador de tipo de instruccion
	 * @return 
	 */
	@GetMapping("/type_instructions/{id}/download")
	public ResponseEntity<?> download(@PathVariable(name = "id", required = true) Long id){
		LOGGER.info("Metodo: GET, Operacion: download, tipoInstruccion a descargar id: {}",id);
		//Dto con info del archivo
		TypeInstrFileDownloadDto typeInsFileDowload = this.typeInstructionService.downloadDoc(id);
		
		//Recurso a descargar
		ByteArrayResource resource = new ByteArrayResource(typeInsFileDowload.getDoc());
		
		//Se crean header para descargar
		LOGGER.info("Metodo: GET, Operacion: download, se crean headers para descargar...");
		HttpHeaders headers = new HttpHeaders();
		//Se obtiene nombre del archivo
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+typeInsFileDowload.getFullName());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
		
		return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(typeInsFileDowload.getDoc().length)
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}
	
}