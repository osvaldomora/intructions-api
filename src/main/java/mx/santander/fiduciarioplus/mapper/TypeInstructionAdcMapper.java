package mx.santander.fiduciarioplus.mapper;

import java.util.ArrayList;
import java.util.List;

import mx.santander.fiduciarioplus.dto.typeInstruction.list.TypeInstructionsFileDto;
import mx.santander.fiduciarioplus.enumeration.FileInstruction;
import mx.santander.fiduciarioplus.enumeration.FileRequired;
import mx.santander.fiduciarioplus.model.TipoInstruccionAdcModel;


public class TypeInstructionAdcMapper {

	//Evita que la clase sea instanciada
	private TypeInstructionAdcMapper() {}
	
	/**
	 * Este metodo permite generar una lista archivos 
	 * @param listTyInstrAdcModel modelo de lista de instrucciones adc
	 * @param valAdici bandera de obligatorierdad de archivo anexo
	 * @return List<TypeInstructionsFileDto> lista de archivos adicionales
	 */
	public static List<TypeInstructionsFileDto> toListDto (List<TipoInstruccionAdcModel> listTyInstrAdcModel, Long valAdici) {
		List<TypeInstructionsFileDto> listTypeIntrsFileDto = new ArrayList<>();
		//Variable tipo de archivo de instruccion
		FileInstruction typeInstruction = null;
		
		//Procesar lista de tipoInstruccionesAdc para genear arreglo de archivos por instruccion
		for(TipoInstruccionAdcModel instrAdcModel : listTyInstrAdcModel) {
			//Se crea instancia
			TypeInstructionsFileDto fileDto = new TypeInstructionsFileDto();
			
			//Se obtiene tipo de archivo instruccion
			typeInstruction = FileInstruction.LAYOUT;
			if(instrAdcModel.getFlgLayou()==0) {
				typeInstruction = FileInstruction.ANEXO;
			}
			fileDto.setId(typeInstruction.getName());
			fileDto.setFileName(instrAdcModel.getDscNombr());
			fileDto.setExtension(typeInstruction.getFileExtension().getExtension());
			//Validaciones de archivos obligatorios
			if(typeInstruction == FileInstruction.LAYOUT) {	//Layout es requerido
				fileDto.setRequired(FileRequired.TRUE.isValue());
			}else {	//Anexos
				//Anexo opcional
				fileDto.setRequired(FileRequired.OPCIONAL.isValue());
				//Anexo obligatorio
				if(valAdici.intValue() == 1) {	
					fileDto.setRequired(FileRequired.TRUE.isValue());
				}
			}
			//Se agrega archivo a la lista
			listTypeIntrsFileDto.add(fileDto);
		}
		//Regresa lista de archivos
		return listTypeIntrsFileDto;
	}
	
}
