package mx.santander.fiduciarioplus.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


import mx.santander.fiduciarioplus.dto.instruction.count.CountInstructionsResDto;
import mx.santander.fiduciarioplus.dto.instruction.list.InstructionsResDto;
import mx.santander.fiduciarioplus.dto.instruction.send.req.SendInstrReqDto;
import mx.santander.fiduciarioplus.dto.instruction.send.res.SendInstrResDto;
import mx.santander.fiduciarioplus.model.InstruccionEnviada;
import mx.santander.fiduciarioplus.model.InstruccionEnviadaModel;


public interface IInstructionSentService {

	List<InstruccionEnviada> findByBucAndNoContrAndNoSubContr(String idBuc, Long idNoContr, Long idNoSubContr);
	
	List<InstruccionEnviada> findByBucAndBusinessAndSubBusinessBetweenDates(String buc, Long business, Long subBusiness);
	
	InstruccionEnviadaModel saveInstruction(SendInstrReqDto instrReqDto);
	
	
	SendInstrResDto saveInstructions(String jsonRequest, List<MultipartFile> files);
	
	InstructionsResDto findAll(String idBuc, Long idNoContr, Long idNoSubContr);
	
	CountInstructionsResDto countInstructions(String buc, Long business, Long subBusiness);
	
}
