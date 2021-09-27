package mx.santander.fiduciarioplus.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import mx.santander.fiduciarioplus.dto.instruction.count.CountInstructionStatusPerDay;
import mx.santander.fiduciarioplus.dto.instruction.count.CountInstructionsDataDto;
import mx.santander.fiduciarioplus.dto.instruction.count.CountInstructionsDatesDto;
import mx.santander.fiduciarioplus.dto.instruction.count.CountInstructionsResDto;
import mx.santander.fiduciarioplus.dto.instruction.count.CountInstructionsStatusDto;
import mx.santander.fiduciarioplus.dto.instruction.list.InstructionsDataDto;
import mx.santander.fiduciarioplus.dto.instruction.list.InstructionsDto;
import mx.santander.fiduciarioplus.dto.instruction.list.InstructionsResDto;
import mx.santander.fiduciarioplus.enumeration.StatusInstruction;
import mx.santander.fiduciarioplus.mapper.InstructionMapper;
import mx.santander.fiduciarioplus.model.InstruccionEnviada;
import mx.santander.fiduciarioplus.repository.IInstructionSendRepository;
import mx.santander.fiduciarioplus.util.DateTool;


@Setter
@Service
public class InstructionSentService implements IInstructionSentService{

	//La Constante LOGGER. Obtiene el Logger de la clase
	private static final Logger LOGGER = LoggerFactory.getLogger(InstructionSentService.class);
	
	@Autowired
	private IInstructionSendRepository instructionSendRepository;
	
	@Override
	public List<InstruccionEnviada> findByBucAndNoContrAndNoSubContr(String idBuc, Long idNoContr, Long idNoSubContr) {
		//Se crea instancia
		List<InstruccionEnviada> listInstructionSend = new ArrayList<>();
		//Fecha hasta 3 meses anteriores
		Calendar date3monthLast = DateTool.getDateMinusOrSumMonth(new Date(), -3);
		LOGGER.info("Fecha 3 meses antes consulta-instrucciones: {}",date3monthLast.getTime());
		
		//Consulta a la BD
		listInstructionSend = this.instructionSendRepository.findByIdFkBucAndIdNoContrAndIdNoSubContrAndFchRegisInsctAfter(idBuc, idNoContr, idNoSubContr, date3monthLast.getTime());
		LOGGER.info("Tamaño de lista de instrucciones: {}",listInstructionSend.size());
				
		return listInstructionSend;
	}

	@Override
	public InstructionsResDto findAll(String idBuc, Long idNoContr, Long idNoSubContr) {
		//Instancia respuesta 
		InstructionsResDto instructionsResDto = InstructionsResDto.builder()
													.data(InstructionsDataDto.builder()
															.build())
													.build();
		//Se realiza consulta
		List<InstruccionEnviada> listInstrSent = this.findByBucAndNoContrAndNoSubContr(idBuc, idNoContr, idNoSubContr);
		if(listInstrSent.isEmpty()) {
			LOGGER.warn("Operacion: listarInstrucciones, method: findAll, buc: {}, business: {}, subBusiness: {}, no se contraron instruciones");
			//Regresa instancia con lista de intrucciones vacia
			return instructionsResDto;
		}
		
		//Se procesa instrucciones model a DTO
		List<InstructionsDto> instructions = new ArrayList<>();
		for(InstruccionEnviada instrSent : listInstrSent) {
			//Se mapea model a DTO
			InstructionsDto instructionDto = InstructionMapper.toDto(instrSent);
			
			/* Inicio
			 * Se setean datos de autorizacion y cause de estatus, url instruccion
			 * ya que no se cuenta por el momento, se realizara en sprint siguientes
			 */
			instructionDto.getFile().setUrl("localhost");
			instructionDto.getTypeInstruction().setAuthorization(false);	//No renecesita autorizacion, por le momento (instruccion sin comite tecnico)
			//Se crea mensaje de causa
			instructionDto.getStatus().setCause("Sin motivo");
			if("RECHAZADA".equalsIgnoreCase(instructionDto.getStatus().getDescription())) {
				instructionDto.getStatus().setCause("No se define la instrucción a detalle");
			}
			/*
			 * Fin 
			 */
			
			//Agrega instruccion a lista
			instructions.add(instructionDto);
		}	
		//Se agrega lista de Instrucciones DTO a respuesta final
		instructionsResDto.getData().setInstructions(instructions);
		return instructionsResDto;
	}

	@Override
	public List<InstruccionEnviada> findByBucAndBusinessAndSubBusinessBetweenDates(String buc, Long business, Long subBusiness) {
		//Se crea instancia
		List<InstruccionEnviada> listInstructionSend = new ArrayList<>();
		
		//Se crean fechas de consultas
		Calendar today = GregorianCalendar.getInstance();
		//Feha 6 dias anteriores
		Calendar startWeek = DateTool.setTime(DateTool.getDateMinusOrSumDay(today.getTime(),-4).getTime(), 0, 0, 0);
		
		//Se valida tipo de consulta a la BD
		if(business==null && subBusiness==null) {	//Consulta solo por buc, todos los negocios y subnegocios
			listInstructionSend = this.instructionSendRepository.findByBucAndDateInstructionBetweenDates(buc, startWeek.getTime(), today.getTime());
		}else {	//Consulta por buc, negocio y subnegocio especifico
			listInstructionSend = this.instructionSendRepository.findStatusByBucAndIdBusinessAndIdSubBusiness(buc, business, subBusiness, startWeek.getTime(), today.getTime());
		}
		LOGGER.info("Tamaño de lista de instrucciones: {}",listInstructionSend.size());
		
		return listInstructionSend;
	}

	@Override
	public CountInstructionsResDto countInstructions(String buc, Long business, Long subBusiness) {
		CountInstructionsResDto countInstructionsResDto = CountInstructionsResDto.builder()
																.data(CountInstructionsDataDto.builder()
																		.build())
																.build();
		
		Calendar dateRequest = GregorianCalendar.getInstance();
		//Se crean fechas de consultas
		Calendar today = GregorianCalendar.getInstance();
		//Feha 6 dias anteriores
		Calendar startWeek = DateTool.setTime(DateTool.getDateMinusOrSumDay(today.getTime(),-6).getTime(), 0, 0, 0);
		Long auxBusiness=business;
		Long auxSubBusiness=subBusiness;
		
		//Recupera datos de consulta
		List<InstruccionEnviada> listInstructionSend = this.findByBucAndBusinessAndSubBusinessBetweenDates(buc, business, subBusiness);
		//Lista de instrucciones vacia, regresa respuesta vacia
		if(listInstructionSend.isEmpty()) {
			LOGGER.info("No hay instrucciones...");
			return countInstructionsResDto;
		}
		
		//Lista de instrucciones por dia a llenar
		List<CountInstructionStatusPerDay> perDay = new ArrayList<>();
		
		for(int i=4; i>=0;i--) {
			Calendar fechaFiltro = DateTool.getDateMinusOrSumDay(dateRequest.getTime(),-i);
			//LOG.info("Fecha Filtro: {}",fechaFiltro.toString());
			int arregloContadorStatus[] = new int [5];
			int totalStatus = 0;
			List<CountInstructionsStatusDto> countInstructionsStatusDto = new ArrayList<>();
			
			List<InstruccionEnviada> listInstructionFiltroDay = listInstructionSend.stream()
															.filter( e -> DateTool.getDay(e.getFchRegisInsct()) == DateTool.getDay(fechaFiltro.getTime()))
															.collect(Collectors.toList());

			
			for (InstruccionEnviada entity : listInstructionFiltroDay) {
				//LOG.info("entity: "+ entity.toString());
				totalStatus += 1;
				switch (entity.getEstatusInstr().getDscNombr()) {
				case "SOLICITADA":
						arregloContadorStatus[0]+=1;
					break;
				case "ENTREGADA":
						arregloContadorStatus[1]+=1;
					break;
				case "EN PROCESO":
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
					.description(StatusInstruction.SOLICITADA.getDescription())
					.quantity(arregloContadorStatus[0]).build());
			
			countInstructionsStatusDto.add(CountInstructionsStatusDto.builder()
					.id(StatusInstruction.ENTREGADA.getId())
					.description(StatusInstruction.ENTREGADA.getDescription())
					.quantity(arregloContadorStatus[1]).build());
			

			countInstructionsStatusDto.add(CountInstructionsStatusDto.builder()
					.id(StatusInstruction.PROCESO.getId())
					.description(StatusInstruction.PROCESO.getDescription())
					.quantity(arregloContadorStatus[2]).build());
			
			countInstructionsStatusDto.add(CountInstructionsStatusDto.builder()
					.id(StatusInstruction.ATENDIDA.getId())
					.description(StatusInstruction.ATENDIDA.getDescription())
					.quantity(arregloContadorStatus[3]).build());
			
			countInstructionsStatusDto.add(CountInstructionsStatusDto.builder()
					.id(StatusInstruction.RECHAZADA.getId())
					.description(StatusInstruction.RECHAZADA.getDescription())
					.quantity(arregloContadorStatus[4]).build());
			LOGGER.info("Fecha Filtro: {} antes de perDay",fechaFiltro.getTime());
			perDay.add(CountInstructionStatusPerDay.builder()
							.date(fechaFiltro.getTime())
							.day(DateTool.getNameDayOfWeek(fechaFiltro.getTime()))
							.status(countInstructionsStatusDto)
							.totalStatus(totalStatus)
							.build());
			
		}
		
		//Se crea respuesta Lista de Estatus por Dia		
		countInstructionsResDto = CountInstructionsResDto.builder()
									.data(CountInstructionsDataDto.builder()
										.buc(buc)
										.business(auxBusiness)
										.subBusiness(auxSubBusiness)
										.statusPerDay(perDay)
										.dates(CountInstructionsDatesDto.builder()
												.start(startWeek.getTime())
												.end(dateRequest.getTime())
												.build())
										.build())
									.build();
		
		return countInstructionsResDto;
	}

}
