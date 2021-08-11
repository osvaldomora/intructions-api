package mx.santander.fiduciarioplus.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.santander.fiduciarioplus.dto.enums.Extension;
import mx.santander.fiduciarioplus.dto.typeinstruction.DataDto;
import mx.santander.fiduciarioplus.dto.typeinstruction.DataTypeInstructionResDto;
import mx.santander.fiduciarioplus.dto.typeinstruction.FileDto;
import mx.santander.fiduciarioplus.dto.typeinstruction.TypeInstructionDto;
import mx.santander.fiduciarioplus.model.typeinstruction.Instruccion;
import mx.santander.fiduciarioplus.repository.ITypeInstructionRepository;

@Service
public class TypeInstructionService implements ITypeInstructionService {


	@Autowired
	ITypeInstructionRepository typeInstructionRepository;
	public static final ModelMapper MODELMAPPER = new ModelMapper();

	@Override
	public DataTypeInstructionResDto getInstructions() {

		List<Instruccion> instruccionEntity = typeInstructionRepository.findAll();
		List<TypeInstructionDto> li = instruccionEntity.stream().map((insEntity) -> {

			TypeInstructionDto typeInstruction = MODELMAPPER.map(insEntity, TypeInstructionDto.class);
			FileDto file = MODELMAPPER.map(insEntity, FileDto.class);
			typeInstruction.setFiles(Arrays.asList(file));

			if (insEntity.getFileId().equalsIgnoreCase("pdf")) {
				file.setExtension(Extension.PDF);
			} else
				file.setExtension(Extension.TXT);
			return typeInstruction;
		}).collect(Collectors.toList());

		DataDto data = new DataDto(li);
		data.setTypeInstructions(li);
		DataTypeInstructionResDto dataTypeInstructionResDto = new DataTypeInstructionResDto(data);

		return dataTypeInstructionResDto;
	}

}
