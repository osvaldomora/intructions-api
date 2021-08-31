package mx.santander.fiduciarioplus.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.pattern.Util;
import mx.santander.fiduciarioplus.dto.countstatesinstructions.CountInstructionStatusPerDay;
import mx.santander.fiduciarioplus.dto.countstatesinstructions.CountInstructionsDataDto;
import mx.santander.fiduciarioplus.dto.countstatesinstructions.CountInstructionsDatesDto;
import mx.santander.fiduciarioplus.dto.countstatesinstructions.CountInstructionsResDto;
import mx.santander.fiduciarioplus.dto.countstatesinstructions.CountInstructionsStatusDto;
import mx.santander.fiduciarioplus.dto.enums.ExtensionFile;
import mx.santander.fiduciarioplus.dto.enums.StatusInstruction;
import mx.santander.fiduciarioplus.dto.listinstructions.*;
import mx.santander.fiduciarioplus.dto.listinstructions.InstructionsResDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.request.DataSendInstructionReqDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.request.FileDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.response.DataDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.response.DataSendInstructionResDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.response.FolioDto;
import mx.santander.fiduciarioplus.exception.catalog.BusinessCatalog;
import mx.santander.fiduciarioplus.exception.catalog.InvalidDataCatalog;
import mx.santander.fiduciarioplus.exception.catalog.PersistenDataCatalog;
import mx.santander.fiduciarioplus.exception.model.BusinessException;
import mx.santander.fiduciarioplus.exception.model.InvalidDataException;
import mx.santander.fiduciarioplus.exception.model.PersistenDataException;
import mx.santander.fiduciarioplus.model.instruction.Instruction;
import mx.santander.fiduciarioplus.model.sendinstruction.SendFIle;
import mx.santander.fiduciarioplus.repository.IInstrucctionFIleRepository;
import mx.santander.fiduciarioplus.repository.IInstructionRepository;
import mx.santander.fiduciarioplus.util.DateUtil;
import mx.santander.fiduciarioplus.util.FileTool;
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
		sendInstructionEntity = Instruction.builder()
							.buc(sendInstructionDto.getBuc().getId())
							.token(sendInstructionDto.getBuc().getToken())
							.date(sendInstructionDto.getDate())
							.idTypeInstruction(sendInstructionDto.getTypeInstruction().getId())
							.nameInstruction(sendInstructionDto.getTypeInstruction().getName())
							.idBusiness(sendInstructionDto.getBusiness().getId())
							.idSubBusiness(sendInstructionDto.getBusiness().getSubBusiness().getId())
							.status(StatusInstruction.SOLICITADA.toString())	//Se agrega por defaul al crear instrucction
							.statusCause("Sin motivo")	//Se agrega por default motivo al crear
							.fileUrl("http://localhost") //Se agrega por defaul al crear instrucction
							.idFolio(folioRandom)
							.build();
		LOG.info("Instruction entity: "+sendInstructionEntity.toString());
		//Se envia registro de Instruccion a BD y valida envio
		Instruction sendInstructionEntityResult =  this.instructionRepository.save(sendInstructionEntity);
		LOG.info("Se a enviado con exito la Instruccion Folio: "+folioRandom);
		if(sendInstructionEntityResult == null) {
			throw new PersistenDataException(PersistenDataCatalog.PSID001, "Error en transaccion al guardar instruccion.");					
		}
		//Se genera respuesta de Folio
		DataSendInstructionResDto dataSendInstructionResDto = DataSendInstructionResDto.builder()
																.data(new DataDto
																			(new FolioDto(sendInstructionEntityResult.getIdFolio(), sendInstructionEntityResult.getDate()))
																		)
																.build();		
		return dataSendInstructionResDto;
	}

	@Override
	public void saveDocument(MultipartFile file) {
		String fileEncode64 = "";
		FileDto fileDto = null;
		SendFIle sendFIleEntity = null;
		try {
			//Se obtiene file en Base 64
			fileEncode64 = FileTool.encode64(file);
			//Se crea Dto del file
			fileDto =FileDto.builder()
						.fileFullName(file.getOriginalFilename())
						.fileName(file.getOriginalFilename().split("\\.")[0])
						.extension(FileTool.getFileExtension(file.getOriginalFilename()))
						.size(file.getSize())
						.fileBase64(fileEncode64)
						.build();
			LOG.info("FileDto: "+fileDto.getFileFullName() +", Size: "+fileDto.getSize());
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
			throw new InvalidDataException(InvalidDataCatalog.INVD002);
		}
	}

	@Override
	public DataSendInstructionResDto save(String sendInstructionJson, List<MultipartFile> files) {
		ObjectMapper mapper = new ObjectMapper();
		DataSendInstructionReqDto dataSendInstructionReqDto = null;
		try {
			 dataSendInstructionReqDto = mapper.readValue(sendInstructionJson, DataSendInstructionReqDto.class);
		} catch (JsonProcessingException | IllegalArgumentException e) {	//Error al Mapear JSON a DTO
			LOG.error("ERROR: INVD.001, Descripcion: Error en la estructura del JSON de entrada.");
			throw new InvalidDataException(InvalidDataCatalog.INVD001, "Error en la estructura del JSON de entrada.");
		}
		//El servicio solo admite un unico documento
		if(files.size() > 1 ||  files.isEmpty()) {	//Se han enviado mas doumentos de los esperados
			LOG.warn("WARN: BUSI.003, Descripcion: Se han enviado mas documentos de los esperados.");
			throw new BusinessException(BusinessCatalog.BUSI003, "Se han enviado mas documentos de los esperados.");
		}
		for(MultipartFile file : files) {
			Long fileSize = file.getSize();
			if(fileSize > MAX_SIZE_FILE_BYTES) {	//Valida tamanio de archivo, tiene que se menor a 15MB
				LOG.warn("WARN: BUSI.001, TAMAÑO ARCHIVO: "+file.getSize());
				throw new BusinessException(BusinessCatalog.BUSI001, "El archivo ha superado el limite de MB.");
			}
			if(fileSize == 0) {	//Valida que el archivo no este vacio
				LOG.warn("WARD: BUSI.001, TAMAÑO ARCHIVO: "+file.getSize());
				throw new BusinessException(BusinessCatalog.BUSI001, "El archivo no puede estar vacio.");
			}
			if(!file.getContentType().equalsIgnoreCase("application/pdf")) {	//Valida tipo de archivo, disponible solo PDF
				LOG.warn("WARN: BUSI.002, FORMATO ARCHIVO: "+file.getContentType());
				throw new BusinessException(BusinessCatalog.BUSI002, "Formato aceptado: " + ExtensionFile.PDF);
			}
			//Enviar archivo
			this.saveDocument(file);
		}
		//Se guarda registro en BD y regresa Folio
		return this.saveInstruction(dataSendInstructionReqDto);
	}

	@Override
	public InstructionsResDto getListInstructions(String buc, Integer business, Integer subBusiness) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateA = new Date();

		
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
					instructionsEntity = instructionRepository.findByBucAndIdBusinessAndIdSubBusiness(buc, business, subBusiness);					
					//Se trata la exception si viene vacia la lista de la entidad 
				}catch (Exception e) {
					// TODO: handle exception
					LOG.error("Error:"+e);
					throw new PersistenDataException(PersistenDataCatalog.PSID001);
				}
				
				for (Instruction entity : instructionsEntity) {
					InstructionDto instructionDto = InstructionDto.builder()
							.folio(entity.getIdFolio())
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
									.authorization(false) //Se agrega como false, ya que el negocio ahorita se necesita asi
									.build())
							.date(format.parse(entity.getDate().toString()))
							/*
							.date(InstructionDto.builder()
									.date(formato.parse(entity.getDate()))
									.build())
							*/
							//.date(InstructionDto.builder().
									//.date(new Date(fo))
									//.date(new Date(entity.parse(getDate())))
									//.build())
							.build();
					instructionsDto.add(instructionDto);
				}
				
				//Se ordena por fecha ascendente
				instructionsDto = instructionsDto.stream().sorted((inst1, inst2) -> inst2.getDate().compareTo(inst1.getDate())).collect(Collectors.toList());
				
				LOG.info("TAMAÑO INSTRUCTIONS DTO: "+instructionsDto.size());
				
				instructionsResDto	=	InstructionsResDto.builder()
											.data(InstructionsDataDto.builder()
													.instructions(instructionsDto)
													.build())
											.build();
				LOG.info("LIST INSTRUCTIONS DTO: "+instructionsDto.toString());
		
		return instructionsResDto;
	}

	@Override
	public CountInstructionsResDto getListCountInstructions(String buc,Integer business, Integer subBusiness) {
		// TODO Auto-generated method stub
		

		Integer auxBusiness=null;
		Integer auxSubBusiness=null;
		
		
		CountInstructionsResDto countInstructionsResDto = null;
		List<Instruction> instructionsEntity = null;
		
		Date today = DateUtil.getFinalDate();
		Date startWeek = DateUtil.getFinalDate(DateUtil.getDateMinusOrSumDay(today,-6));
		
		LOG.info("Fecha Hoy: {}",today.toString());
		LOG.info("Fecha inicio Semana (-7 dias): {}", startWeek);
			if(business==null && subBusiness==null) {
				instructionsEntity = instructionRepository.findByBucAndDateInstructionBetweenDates(buc, startWeek, today);
			}else {
				if(subBusiness == null ) {	//Arroja error si no se envia el subBusiness.id, no se puede generar consulta sin este dato				
					throw new PersistenDataException(PersistenDataCatalog.PSID003,"Se requiere subBusiness.id, para realizar una búsqueda completa");
				}
				if(business == null ) {	//Arroja error si no se envia el  business.id, no se puede generar consulta sin este dato	
					throw new PersistenDataException(PersistenDataCatalog.PSID003,"Se requiere business.id, para realizar una búsqueda completa");
				}
				instructionsEntity = instructionRepository.findStatusByBucAndIdBusinessAndIdSubBusiness(buc,business,subBusiness, startWeek, today);
				auxBusiness=business;
				auxSubBusiness=subBusiness;
			}
			
			LOG.info("Tamaño de instructionsEntity_countStatus: {}",instructionsEntity.size());
			for(Instruction e : instructionsEntity) {
				LOG.info("Entity: {}",e.toString());
			}
			if(instructionsEntity.isEmpty()) {	//Arraja error al no encontrar datos desde la BD
				LOG.error("Error al listar countstatus, no se encuntra el recurso solicitado.");
				throw new PersistenDataException(PersistenDataCatalog.PSID003,"Error al listar countstatus, no se encuntra el recurso solicitado");
			}

			List<CountInstructionStatusPerDay> perDay = new ArrayList<>();
			for(int i=6; i>=0;i--) {
				Date fechaFiltro = DateUtil.getDateMinusOrSumDay(today,-i);
				LOG.info("Fecha Filtro: {}",fechaFiltro.toString());
				int arregloContadorStatus[] = new int [5];
				List<CountInstructionsStatusDto> listStatus = new ArrayList<>();
				List<CountInstructionsStatusDto> countInstructionsStatusDto = new ArrayList<>();
				List<Instruction> instructionsEntFiltroDay = instructionsEntity.stream().filter( e -> e.getDate().getDay() == fechaFiltro.getDay()).collect(Collectors.toList());
				int totalStatus = 0;
				
				for (Instruction entity : instructionsEntFiltroDay) {
					LOG.info("entity: "+ entity.toString());
					totalStatus += 1;
					switch (entity.getStatus()) {
					case "SOLICITADA":
							arregloContadorStatus[0]+=1;
						break;
					case "ENTREGADA":
							arregloContadorStatus[1]+=1;
						break;
					case "PROCESO":
							arregloContadorStatus[2]+=1;
						break;
					case "ATENDIDA":
							arregloContadorStatus[3]+=1;
						break;
					case "RECHAZADA":
							arregloContadorStatus[4]+=1;
						break;
					}
				}
				
				countInstructionsStatusDto.add(CountInstructionsStatusDto.builder()
						.id(StatusInstruction.SOLICITADA.getId())
						.description(StatusInstruction.SOLICITADA.toString())
						.quantity(arregloContadorStatus[0]).build());
				
				countInstructionsStatusDto.add(CountInstructionsStatusDto.builder()
						.id(StatusInstruction.ENTREGADA.getId())
						.description(StatusInstruction.ENTREGADA.toString())
						.quantity(arregloContadorStatus[1]).build());
				

				countInstructionsStatusDto.add(CountInstructionsStatusDto.builder()
						.id(StatusInstruction.PROCESO.getId())
						.description(StatusInstruction.PROCESO.toString())
						.quantity(arregloContadorStatus[2]).build());
				
				countInstructionsStatusDto.add(CountInstructionsStatusDto.builder()
						.id(StatusInstruction.ATENDIDA.getId())
						.description(StatusInstruction.ATENDIDA.toString())
						.quantity(arregloContadorStatus[3]).build());
				
				countInstructionsStatusDto.add(CountInstructionsStatusDto.builder()
						.id(StatusInstruction.RECHAZADA.getId())
						.description(StatusInstruction.RECHAZADA.toString())
						.quantity(arregloContadorStatus[4]).build());
				LOG.info("Fecha Filtro: {} antes de perDay",fechaFiltro.toString());
				perDay.add(CountInstructionStatusPerDay.builder()
								.date(fechaFiltro)
								.day(DateUtil.getDayOfWeek(fechaFiltro))
								.status(countInstructionsStatusDto)
								.totalStatus(totalStatus)
								.build());
			}
			
			
			
			
			
		//Se crea respuesta final DTO		
		countInstructionsResDto = CountInstructionsResDto.builder()
				.data(CountInstructionsDataDto.builder()
						.buc(instructionsEntity.get(0).getBuc().toString())
						.business(auxBusiness)
						.subBusiness(auxSubBusiness)
						.statusPerDay(perDay)
						.dates(CountInstructionsDatesDto.builder()
								.start(startWeek)
								.end(today)
								.build())
						.build())
				.build();
		
		return countInstructionsResDto;
	}
	
	
	

}
