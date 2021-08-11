package mx.santander.fiduciarioplus.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.santander.fiduciarioplus.dto.sendinstruction.request.DataSendInstructionReqDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.request.FileDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.response.DataDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.response.DataSendInstructionResDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.response.FolioDto;
import mx.santander.fiduciarioplus.model.sendinstruction.SendFIle;
import mx.santander.fiduciarioplus.model.sendinstruction.SendInstruction;
import mx.santander.fiduciarioplus.repository.IInstrucctionFIleRepository;
import mx.santander.fiduciarioplus.repository.IInstructionRepository;
import mx.santander.fiduciarioplus.util.FileEncoder;
import mx.santander.fiduciarioplus.util.FolioRandom;

@Service
public class IntructionService implements IInstructionService{
	
	@Autowired
	private IInstructionRepository instructionRepository;
	
	@Autowired
	private IInstrucctionFIleRepository instrucctionFIleRepository;
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private final Long MAX_SIZE_FILE_BYTES = 15728640L;

	@Override
	public DataSendInstructionResDto saveInstruction(DataSendInstructionReqDto sendInstructionDto) {
		SendInstruction sendInstructionEntity = null;
		String folioRandom = FolioRandom.getRandomFolio();
		LOG.info("SendInstruction: "+sendInstructionDto.toString());
		sendInstructionEntity = SendInstruction.builder()
							.buc(sendInstructionDto.getBuc().getId())
							.token(sendInstructionDto.getBuc().getToken())
							.date(sendInstructionDto.getDate())
							.idTypeInstruction(sendInstructionDto.getTypeInstruction().getId())
							.nameInstruction(sendInstructionDto.getTypeInstruction().getName())
							.idBusiness(sendInstructionDto.getBusiness().getId())
							.idSubBusiness(sendInstructionDto.getBusiness().getSubBusiness().getId())
							.idFolio(folioRandom)
							.build();
		//Se envia registro de Instruccion a BD y valida envio
		SendInstruction sendInstructionEntityResult =  this.instructionRepository.save(sendInstructionEntity);
		if(sendInstructionEntityResult == null) {
			throw new RuntimeException("Error al guardar instruccion en BD");
		}
		//Se genera respuesta de Folio
		DataSendInstructionResDto dataSendInstructionResDto = DataSendInstructionResDto.builder()
																.dataDto(new DataDto
																			(new FolioDto(sendInstructionEntityResult.getIdFolio(), sendInstructionEntityResult.getDate()))
																		)
																.build();		
		// TODO Auto-generated method stub
		return dataSendInstructionResDto;
	}

	@Override
	public void saveDocument(MultipartFile file) {
		String fileEncode64 = "";
		FileDto fileDto = null;
		SendFIle sendFIleEntity = null;
		try {
			//Se obtiene file en Base 64
			fileEncode64 = FileEncoder.encode64(file);
			//Se crea Dto del file
			fileDto =FileDto.builder()
						.fileFullName(file.getOriginalFilename())
						.fileName(file.getOriginalFilename().split("\\.")[0])
						.extension(file.getOriginalFilename().split("\\.")[1])
						.size(file.getSize())
						.fileBase64(fileEncode64)
						.build();
			LOG.info("FileDto: "+fileDto.toString());
			//Se hace map de DTO a Entity
			sendFIleEntity = SendFIle.builder()
							.fileFullName(fileDto.getFileFullName())
							.fileName(fileDto.getFileName())
							.extension(fileDto.getExtension())
							.size(fileDto.getSize())
							.fileBase64(fileDto.getFileBase64())
							.build();
			//Guarda en repository (Simulacion de FileNet)
			this.instrucctionFIleRepository.save(sendFIleEntity);
			LOG.info("Se a enviado con exito el archivo: "+fileDto.getFileFullName());
		} catch (IOException e) {
			throw new RuntimeException("Error al codificar archivo a base64: "+e.getMessage());
		}
		LOG.info("Archivo codificado base64: "+fileEncode64);
		
	}

	@Override
	public DataSendInstructionResDto save(String sendInstructionJson, List<MultipartFile> files) {
		ObjectMapper mapper = new ObjectMapper();
		DataSendInstructionReqDto dataSendInstructionReqDto = null;

		try {
			 dataSendInstructionReqDto = mapper.readValue(sendInstructionJson, DataSendInstructionReqDto.class);
		} catch (JsonProcessingException | IllegalArgumentException e) {
			throw new RuntimeException("Error al mapear JSON a DTO: "+e.getMessage());
		}
		//El servicio solo admite un unico documento
		LOG.info("Tamaño lista archivos: "+String.valueOf(files.size()));
		LOG.info("Lista vacia: "+String.valueOf(files.isEmpty()));
		if(files.size() > 1 ||  files.isEmpty()) {
			throw new RuntimeException("Se han enviado mas documentos de los esperados");
		}
		for(MultipartFile file : files) {
			LOG.info("Tamaño de archivo en KB: "+file.getSize());
			if(file.getSize() > MAX_SIZE_FILE_BYTES) {
				throw new RuntimeException("Se ha superado el tamanio maximo del documento");
			}
			if(file.getSize() == 0) {
				throw new RuntimeException("El documento esta vacio");
			}
			//Enviar archivo
			this.saveDocument(file);
		}
		//Se guarda registro en BD y regresa Folio
		return this.saveInstruction(dataSendInstructionReqDto);
	}

}
