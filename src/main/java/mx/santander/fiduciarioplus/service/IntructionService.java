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
import mx.santander.fiduciarioplus.repository.IInstructionRepository;
import mx.santander.fiduciarioplus.util.FileEncoder;

@Service
public class IntructionService implements IInstructionService{
	
	@Autowired
	private IInstructionRepository instructionRepository;
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private final Long MAX_SIZE_FILE = 15360L;

	@Override
	public DataSendInstructionResDto saveInstruction(DataSendInstructionReqDto sendInstruction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDocument(MultipartFile file) {
		
		String fileEncode64 = "";
		try {
			fileEncode64 = FileEncoder.encode64(file);
		} catch (IOException e) {
			throw new RuntimeException("Error al codificar archivo a base64: "+e.getMessage());
		}
		LOG.info("Archivo codificado base64: "+fileEncode64);
		
	}

	@Override
	public DataSendInstructionResDto save(String sendInstructionJson, List<MultipartFile> files) {
		ObjectMapper mapper = new ObjectMapper();
		DataSendInstructionReqDto dataSendInstructionReqDto = null;
		FileDto fileDto = null;
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
			if(file.getSize() > MAX_SIZE_FILE) {
				throw new RuntimeException("Se ha superado el tamanio maximo del documento");
			}
			if(file.getSize() == 0) {
				throw new RuntimeException("El documento esta vacio");
			}
			fileDto = FileDto.builder()
							.fileFullName(file.getOriginalFilename())
							.fileName(file.getName())
							.extension(file.getOriginalFilename().split("\\.")[1])
							.size(file.getSize())
							.build();
			//Enviar archivo
			this.saveDocument(file);
		}
			
		
		
		
		
		LOG.info("SendInstruction: "+dataSendInstructionReqDto.toString());
		LOG.info("FileDto: "+fileDto.toString());
		
		DataSendInstructionResDto res = new DataSendInstructionResDto();
		FolioDto folioDto = new FolioDto();
		folioDto.setId("F123456789");
		DataDto dataDto = new  DataDto();
		dataDto.setFolioDto(folioDto);
		res.setDataDto(dataDto);
		return res;
	}

}
