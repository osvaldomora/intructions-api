package mx.santander.fiduciarioplus.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.santander.fiduciarioplus.dto.instruction.send.req.SendInstrFileDto;
import mx.santander.fiduciarioplus.dto.instruction.send.req.SendInstrReqDto;
import mx.santander.fiduciarioplus.enumeration.FileInstruction;
import mx.santander.fiduciarioplus.model.RegistroDocumentoModel;
import mx.santander.fiduciarioplus.repository.IDocumentRegistrationRepository;


@Service
public class DocumentRegistrationService implements IDocumentRegistrationService{

	// La Constante LOGGER. Obtiene el Logger de la clase
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentRegistrationService.class);
	
	//Variable de repositorio de Registro de documento
	@Autowired
	private IDocumentRegistrationRepository documentRegistrationRepository;
	
	//Constante quien envia el contrato (parteContrato)
	private final String PARTE_CONTRATO = "FIDEICOMITENTE";
	
	//Constante archivo individual
	private final String INDIVIDUAL_MASIVO = "I";
	
	//Contante al enviar archivo, pendiente
	private final String STATUS_APLI_DOC = "PE";
	
	//Constante del estatus del documento: archivado
	private final String STATUS_DOC = "AR";
	
	//Constante del documento copia u origial: proviene de Web
	private final String COPIA_ORIGINAL = "W";
	
	@Override
	public RegistroDocumentoModel save(RegistroDocumentoModel documentoModel) {
		//Se registra en BD
		documentoModel = this.documentRegistrationRepository.save(documentoModel);
		LOGGER.info("Servicio: DocumentRegistrationService, Operacion: save, se guarda registroDocumento con exito: {}",documentoModel.toString());
		return documentoModel;
	}

	@Override
	public RegistroDocumentoModel saveRegistrationDoc(SendInstrReqDto instrReqDto, SendInstrFileDto fileDto) {
		//Se obtiene valor de variables
		String claveTipoDoc = String.valueOf(instrReqDto.getTypeInstruction().getCodeDoc());
		String conceptDoc = instrReqDto.getTypeInstruction().getName() + "-" + instrReqDto.getBusiness().getId() + "-" + instrReqDto.getBusiness().getSubBusiness().getId();
		Long clavePo = 999L; 	/*instrReqDto.getBuc().getClavePo(); aun no se envia*/
		String docDetonante = "N";	//Default
		if(FileInstruction.INSTRUCCION.getName().equalsIgnoreCase(fileDto.getType())) {
			docDetonante = "S";
		}
		//Se crea modelo
		RegistroDocumentoModel documentoModel = RegistroDocumentoModel.builder()
													.claveTipoDoc(claveTipoDoc)
													.numeroDoc(null)
													.conceptoDoc(conceptDoc)
													.parteContrato(PARTE_CONTRATO)
													.clavePo(clavePo)
													.testimonioCertifSimple(null)
													.fechaVencimientoDoc(null)
													.fechaRecepcion(new Date())
													.fechaExpedicion(new Date())
													.fechaEntradaArchivo(null)
													.individualMasivo(INDIVIDUAL_MASIVO)
													.cifraControl(null)
													.estatusAplicacionDoc(STATUS_APLI_DOC)
													.estatusDoc(STATUS_DOC)
													.claveInmueble(null)
													.copiaOriginal(COPIA_ORIGINAL)
													.flgDetnt(docDetonante)
													.idDocDetnt(null)
													.valEstInteg(null)
													.flgReqTraza(null)
													.flgReqOper(null)
													.build();
		LOGGER.info("Servicio: DocumentRegistrationService, Operacion: saveRegistrationDoc, se crea RegistraDocumentoModelo: {}",documentoModel.toString());
		//Se procede a registrar
		documentoModel = this.save(documentoModel);
		return documentoModel;
	}
	
	

}
