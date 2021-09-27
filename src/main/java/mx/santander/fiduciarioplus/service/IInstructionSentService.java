package mx.santander.fiduciarioplus.service;

import java.util.List;

import mx.santander.fiduciarioplus.dto.instruction.count.CountInstructionsResDto;
import mx.santander.fiduciarioplus.dto.instruction.list.InstructionsResDto;
import mx.santander.fiduciarioplus.model.InstruccionEnviada;


public interface IInstructionSentService {

	List<InstruccionEnviada> findByBucAndNoContrAndNoSubContr(String idBuc, Long idNoContr, Long idNoSubContr);
	
	List<InstruccionEnviada> findByBucAndBusinessAndSubBusinessBetweenDates(String buc, Long business, Long subBusiness);
	
	
	InstructionsResDto findAll(String idBuc, Long idNoContr, Long idNoSubContr);
	
	CountInstructionsResDto countInstructions(String buc, Long business, Long subBusiness);
	
}
