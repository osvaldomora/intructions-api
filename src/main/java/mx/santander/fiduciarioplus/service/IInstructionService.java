package mx.santander.fiduciarioplus.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mx.santander.fiduciarioplus.dto.listinstructions.InstructionsResDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.request.DataSendInstructionReqDto;
import mx.santander.fiduciarioplus.dto.sendinstruction.response.DataSendInstructionResDto;

public interface IInstructionService {
	DataSendInstructionResDto saveInstruction(DataSendInstructionReqDto sendInstruction);
	void saveDocument (MultipartFile file);
	DataSendInstructionResDto save (String sendInstructionJson, List<MultipartFile> files);
	InstructionsResDto getListInstructions(String buc);
}
