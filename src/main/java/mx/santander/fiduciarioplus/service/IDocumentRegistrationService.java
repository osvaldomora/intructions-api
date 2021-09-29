package mx.santander.fiduciarioplus.service;

import mx.santander.fiduciarioplus.dto.instruction.send.req.SendInstrFileDto;
import mx.santander.fiduciarioplus.dto.instruction.send.req.SendInstrReqDto;
import mx.santander.fiduciarioplus.model.RegistroDocumentoModel;

public interface IDocumentRegistrationService {

	RegistroDocumentoModel save(RegistroDocumentoModel documentoModel);
	
	RegistroDocumentoModel saveRegistrationDoc(SendInstrReqDto instrReqDto, SendInstrFileDto fileDto);
	
}
