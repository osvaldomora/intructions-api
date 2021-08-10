package mx.santander.fiduciarioplus.service;

import java.util.List;

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

@Service
public class IntructionService implements IInstructionService{
	
	@Autowired
	private IInstructionRepository instructionRepository;
	
	private final Long MAX_SIZE_FILE = 15360L;

	@Override
	public DataSendInstructionResDto saveInstruction(DataSendInstructionReqDto sendInstruction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDocument(MultipartFile file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DataSendInstructionResDto save(String sendInstructionJson, List<MultipartFile> files) {
		ObjectMapper mapper = new ObjectMapper();
		DataSendInstructionReqDto dataSendInstructionReqDto = null;
		FileDto fileDto = null;
		try {
			 dataSendInstructionReqDto = mapper.readValue(sendInstructionJson, DataSendInstructionReqDto.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error al mapear JSON a DTO: "+e.getMessage());
		}
		//El servicio solo admite un unico documento
		if(files.size() > 0 && files.size() < 2 && !files.isEmpty()) {
			for(MultipartFile file : files) {
				if(file.getSize() > MAX_SIZE_FILE) {
					throw new RuntimeException("Se ha superado el tamanio maximo del documento");
				}
				fileDto = FileDto.builder()
									.fileFullName(file.getOriginalFilename())
									.fileName(file.getName())
									.extension(file.getOriginalFilename().split("\\.")[1])
									.size(file.getSize())
									.build();
			}
		}else {
			throw new RuntimeException("Se han enviado mas documentos de los esperados");
		}
		
		
		
		System.out.println("SendInstruction: "+dataSendInstructionReqDto.toString());
		System.out.println("FileDto: "+fileDto.toString());
		
		DataSendInstructionResDto res = new DataSendInstructionResDto();
		FolioDto folioDto = new FolioDto();
		folioDto.setId("F123456789");
		DataDto dataDto = new  DataDto();
		dataDto.setFolioDto(folioDto);
		res.setDataDto(dataDto);
		return res;
	}

}
