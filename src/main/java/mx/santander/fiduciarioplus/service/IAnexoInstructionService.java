package mx.santander.fiduciarioplus.service;

import org.springframework.web.multipart.MultipartFile;
import mx.santander.fiduciarioplus.model.InstruccionAnexoModel;


public interface IAnexoInstructionService {

	InstruccionAnexoModel save(Long idInstrNvas, Long folio, MultipartFile file, String tipoArchivo);
	
}
