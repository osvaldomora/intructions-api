package mx.santander.fiduciarioplus.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.santander.fiduciarioplus.dto.enums.ExtensionFile;
import mx.santander.fiduciarioplus.dto.enums.StatusInstruction;
import mx.santander.fiduciarioplus.dto.listinstructions.*;
import mx.santander.fiduciarioplus.dto.listinstructions.InstructionsResDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.request.DataSendInstructionReqDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.request.FileDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.response.DataDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.response.DataSendInstructionResDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.response.FolioDto;
import mx.santander.fiduciarioplus.exception.BusinessException;
import mx.santander.fiduciarioplus.exception.InvalidDataException;
import mx.santander.fiduciarioplus.exception.PersistentException;
import mx.santander.fiduciarioplus.exception.catalog.BusinessCatalog;
import mx.santander.fiduciarioplus.exception.catalog.InvalidDataCatalog;
import mx.santander.fiduciarioplus.exception.catalog.LevelException;
import mx.santander.fiduciarioplus.exception.catalog.PersistentDataCatalog;
import mx.santander.fiduciarioplus.model.instruction.Instruction;
import mx.santander.fiduciarioplus.model.sendinstruction.SendFIle;
import mx.santander.fiduciarioplus.repository.IInstrucctionFIleRepository;
import mx.santander.fiduciarioplus.repository.IInstructionRepository;
import mx.santander.fiduciarioplus.util.FileEncoder;
import mx.santander.fiduciarioplus.util.FolioRandom;

//import java.util.Date;

@Service
public class IntructionService implements IInstructionService{
	
	@Autowired
	private IInstructionRepository instructionRepository;
	
	@Autowired
	private IInstrucctionFIleRepository instrucctionFIleRepository;
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private final Long MAX_SIZE_FILE_BYTES = 15728640L;
	
	SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	//Date fechaActual = new Date();

	@Override
	public DataSendInstructionResDto saveInstruction(DataSendInstructionReqDto sendInstructionDto) {
		Instruction sendInstructionEntity = null;
		String folioRandom = FolioRandom.getRandomFolio();
		LOG.info("SendInstruction: "+sendInstructionDto.toString());
		sendInstructionEntity = Instruction.builder()
							.buc(sendInstructionDto.getBuc().getId())
							.token(sendInstructionDto.getBuc().getToken())
							.date(sendInstructionDto.getDate())
							.idTypeInstruction(sendInstructionDto.getTypeInstruction().getId())
							.nameInstruction(sendInstructionDto.getTypeInstruction().getName())
							.idBusiness(sendInstructionDto.getBusiness().getId())
							.idSubBusiness(sendInstructionDto.getBusiness().getSubBusiness().getId())
							.status(StatusInstruction.SOLICITADA.toString())	//Se agrega por defaul al crear instrucction
							.fileUrl("http://localhost") //Se agrega por defaul al crear instrucction
							.idFolio(folioRandom)
							.build();
		//Se envia registro de Instruccion a BD y valida envio
		Instruction sendInstructionEntityResult =  this.instructionRepository.save(sendInstructionEntity);
		if(sendInstructionEntityResult == null) {
			throw new PersistentException(HttpStatus.CONFLICT,
						PersistentDataCatalog.PSID001.getCode(), 
						PersistentDataCatalog.PSID001.getMessage(),
						LevelException.ERROR.toString(), 
						"Error en transaccion de guardar instruction.");
					
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
		} catch (IOException e) {	//Error al codificar archivo 
			throw new InvalidDataException(HttpStatus.CONFLICT, 
					InvalidDataCatalog.INVD002.getCode(),
					InvalidDataCatalog.INVD002.getMessage(), 
					LevelException.ERROR.toString(), 
					"Error al codificar archivo.");
		}
		LOG.info("Archivo codificado base64: "+fileEncode64);
		
	}

	@Override
	public DataSendInstructionResDto save(String sendInstructionJson, List<MultipartFile> files) {
		ObjectMapper mapper = new ObjectMapper();
		DataSendInstructionReqDto dataSendInstructionReqDto = null;

		try {
			 dataSendInstructionReqDto = mapper.readValue(sendInstructionJson, DataSendInstructionReqDto.class);
		} catch (JsonProcessingException | IllegalArgumentException e) {	//Error al Mapear JSON a DTO
			throw new InvalidDataException(HttpStatus.CONFLICT, 
					InvalidDataCatalog.INVD001.getCode(),
					InvalidDataCatalog.INVD001.getMessage(), 
					LevelException.ERROR.toString(), 
					"Error al mapear JSON");
		}
		//El servicio solo admite un unico documento
		LOG.info("Tamaño lista archivos: "+String.valueOf(files.size()));
		LOG.info("Lista vacia: "+String.valueOf(files.isEmpty()));
		if(files.size() > 1 ||  files.isEmpty()) {	//Se han enviado mas doumentos de los esperados
			throw new BusinessException(HttpStatus.BAD_REQUEST,
					BusinessCatalog.BUSI003.getCode(),
					BusinessCatalog.BUSI003.getMessage(),
					LevelException.ERROR.toString(),
					"Se han enviado mas documentos de los esperados.");
		}
		for(MultipartFile file : files) {
			LOG.info("Tamaño de archivo en KB: "+file.getSize());
			if(file.getSize() > MAX_SIZE_FILE_BYTES) {	//Valida tamanio de archivo, tiene que se menor a 15MB
				throw new BusinessException(HttpStatus.BAD_REQUEST,
						BusinessCatalog.BUSI001.getCode(),
						BusinessCatalog.BUSI001.getMessage(),
						LevelException.ERROR.toString(),
						"El archivo ha superado el limite de 15MB.");
			}
			if(!file.getOriginalFilename().split("\\.")[1].equalsIgnoreCase(ExtensionFile.PDF.toString())) {	//Valida tipo de archivo, disponible solo PDF
				throw new BusinessException(HttpStatus.BAD_REQUEST,
						BusinessCatalog.BUSI002.getCode(),
						BusinessCatalog.BUSI002.getMessage(),
						LevelException.ERROR.toString(),
						"Formato aceptado: PDF.");
			}
			if(file.getSize() == 0) {	//Valida que el archivo no este vacio
				throw new BusinessException(HttpStatus.BAD_REQUEST,
											BusinessCatalog.BUSI001.getCode(),
											BusinessCatalog.BUSI001.getMessage(),
											LevelException.ERROR.toString(),
											"El archivo no puede estar vacio");
			}
			//Enviar archivo
			this.saveDocument(file);
		}
		//Se guarda registro en BD y regresa Folio
		return this.saveInstruction(dataSendInstructionReqDto);
	}

	@Override
	public InstructionsResDto getListInstructions(String buc) {
		// TODO Auto-generated method stub
		//Se crea el objeto de instructionsResDto
				InstructionsResDto instructionsResDto = null;
				//Se crea una lista de la entidad
				 List<Instruction> instructionsEntity=null;
				//Se crea un lista para los DTO 
				List<InstructionDto> instructionsDto = new ArrayList<>();
				
				/*
				 //Se crea una lista de la entidad
				 List<Instruction> instructionsEntity=null;
				 //Se crea un lista para los DTO 
				 List<InstructionDto> instructionsDto = new ArrayList<>();
				 * 
				 */
				
				try {
					//Se obtienen todos los valores de la entidad 
					//instructionsEntity = instructionRepository.findAll();
					instructionsEntity = instructionRepository.findByBuc(buc);
					LOG.info("TAMAÑO INSTRUCTIONS ENTITY: "+instructionsEntity.size());
					for(Instruction i : instructionsEntity) {
						LOG.info(i.getIdInstruction().toString());
					}
					
					//Se trata la exception si viene vacia la lista de la entidad 
				}catch (Exception e) {
					// TODO: handle exception
					LOG.info("Error:"+e);
					throw new PersistentException(HttpStatus.CONFLICT, PersistentDataCatalog.PSID001.getCode(),
							PersistentDataCatalog.PSID001.getMessage(), LevelException.ERROR.toString(),
							"No existen datos en la base de datos");
				}
				
				for (Instruction entity : instructionsEntity) {
					InstructionDto instructionDto = InstructionDto.builder()
							.business(InstructionBusinessDto.builder()
									.id(entity.getIdBusiness())
									.build())
							.file(InstructionsFileDto.builder()
									.url(entity.getFileUrl())
									.build())
							.status(StatusDto.builder()
									.description(entity.getStatus())
									.cause(entity.getStatusCause())
									.build())
							.subBusiness(SubBusinessDto.builder()
									.id(entity.getIdSubBusiness())
									.build())
							.typeInstruction(InstructionsTypeInstructionDto.builder()
									.id(entity.getIdTypeInstruction())
									.name(entity.getNameInstruction())
									.build())
							.date(InstructionDto.builder()
									.date(formato.parse(entity.getDate()))
									.build())
							//.date(InstructionDto.builder().
									//.date(new Date(fo))
									//.date(new Date(entity.parse(getDate())))
									//.build())
							.build();
					instructionsDto.add(instructionDto);
				}
				LOG.info("TAMAÑO INSTRUCTIONS DTO: "+instructionsDto.size());
				
				instructionsResDto	=	InstructionsResDto.builder()
											.data(InstructionsDataDto.builder()
													.instructions(instructionsDto)
													.build())
											.build();
		
		
		return instructionsResDto;
	}

}
