package mx.santander.fiduciarioplus.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.santander.fiduciarioplus.dto.typeinstruction.DataDto;
import mx.santander.fiduciarioplus.dto.typeinstruction.File;
import mx.santander.fiduciarioplus.dto.typeinstruction.TypeInstruction;
import mx.santander.fiduciarioplus.dto.typeinstruction.enums.Extension;
import mx.santander.fiduciarioplus.model.typeinstruction.Instruccion;
import mx.santander.fiduciarioplus.repository.IInstructionRepository;

@Service
public class InstructionService implements IInstructionService {

//	private InstructionDto instDto = new InstructionDto();

	@Autowired
	IInstructionRepository instructionRepository;
	public static final ModelMapper MODELMAPPER = new ModelMapper();

	@Override
	public DataDto getInstructions() {

		List<Instruccion> instruccionEntity = instructionRepository.findAll();
		List<TypeInstruction> li = instruccionEntity.stream().map((insEntity) -> {

			TypeInstruction typeInstruction = MODELMAPPER.map(insEntity, TypeInstruction.class);
			File file = MODELMAPPER.map(insEntity, File.class);
			typeInstruction.setFile(Arrays.asList(file));

			if (insEntity.getId_documento().equalsIgnoreCase("pdf")) {
				file.setExtension(Extension.PDF);
			} else
				file.setExtension(Extension.TXT);
			return typeInstruction;
		}).collect(Collectors.toList());

//		instDto.setResultado(new Resultado(200,"ok"));	
		DataDto data = new DataDto(li);
		data.setTypeInstruction(li);

		return data;
	}

}
