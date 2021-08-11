package mx.santander.fiduciarioplus.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import mx.santander.fiduciarioplus.dto.enums.Extension;
import mx.santander.fiduciarioplus.dto.typeinstruction.DataDto;
import mx.santander.fiduciarioplus.dto.typeinstruction.DataTypeInstructionResDto;
import mx.santander.fiduciarioplus.dto.typeinstruction.FileDto;
import mx.santander.fiduciarioplus.dto.typeinstruction.TypeInstructionDto;
import mx.santander.fiduciarioplus.exception.BusinessException;
import mx.santander.fiduciarioplus.exception.InvalidDataException;
import mx.santander.fiduciarioplus.exception.PersistentException;
import mx.santander.fiduciarioplus.exception.catalog.BusinessCatalog;
import mx.santander.fiduciarioplus.exception.catalog.InvalidDataCatalog;
import mx.santander.fiduciarioplus.exception.catalog.LevelException;
import mx.santander.fiduciarioplus.exception.catalog.PersistentDataCatalog;
import mx.santander.fiduciarioplus.model.typeinstruction.Instruccion;
import mx.santander.fiduciarioplus.repository.ITypeInstructionRepository;

@Service
public class TypeInstructionService implements ITypeInstructionService {

	@Autowired
	ITypeInstructionRepository typeInstructionRepository;
	public static final ModelMapper MODELMAPPER = new ModelMapper();

	@Override
	public DataTypeInstructionResDto getInstructions() {
		DataTypeInstructionResDto dataTypeInstructionResDto = null;
		List<Instruccion> instruccionEntity = typeInstructionRepository.findAll();
		if (instruccionEntity.isEmpty()) {
			throw new PersistentException(HttpStatus.CONFLICT, PersistentDataCatalog.PSID001.getCode(),
					PersistentDataCatalog.PSID001.getMessage(), LevelException.ERROR.toString(),
					"No se encontraron datos en la base de datos.");
		}

		try {
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
			dataTypeInstructionResDto = new DataTypeInstructionResDto(data);
		} catch (Exception e) {
			throw new InvalidDataException(HttpStatus.CONFLICT, InvalidDataCatalog.INVD001.getCode(),
					InvalidDataCatalog.INVD001.getMessage(), LevelException.ERROR.toString(), "Error al mapear el DTO");
		}
		return dataTypeInstructionResDto;
	}

}
