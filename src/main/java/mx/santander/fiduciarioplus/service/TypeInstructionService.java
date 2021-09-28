package mx.santander.fiduciarioplus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import mx.santander.fiduciarioplus.dto.typeInstruction.download.TypeInstrFileDownloadDto;
import mx.santander.fiduciarioplus.dto.typeInstruction.list.TypeInstructionDto;
import mx.santander.fiduciarioplus.dto.typeInstruction.list.TypeInstructionsDataDto;
import mx.santander.fiduciarioplus.dto.typeInstruction.list.TypeInstructionsDataResDto;
import mx.santander.fiduciarioplus.dto.typeInstruction.list.TypeInstructionsFileDto;
import mx.santander.fiduciarioplus.enumeration.FileInstruction;
import mx.santander.fiduciarioplus.enumeration.FileRequired;
import mx.santander.fiduciarioplus.exception.PersistenDataException;
import mx.santander.fiduciarioplus.exception.catalog.PersistenDataCatalog;
import mx.santander.fiduciarioplus.mapper.TypeInstrFIleDownloadMapper;
import mx.santander.fiduciarioplus.mapper.TypeInstructionAdcMapper;
import mx.santander.fiduciarioplus.model.TipoInstruccionAdcModel;
import mx.santander.fiduciarioplus.model.TipoInstruccionModel;
import mx.santander.fiduciarioplus.repository.ITypeInstructionAdcRepository;
import mx.santander.fiduciarioplus.repository.ITypeInstructionRepository;
import mx.santander.fiduciarioplus.util.FileTool;


/**
 * Esta clase Service esta asociada al negocio de tipo de instrucciones.
 * 
 * Es servicio de negocio, permite realizar y hacer las validaciones necesarias para generar la respuesta esperada,
 * de acuerdo a lo establecido por el negocio.
 * 
 * Se cuenta con los sigguientes repositorios:
 * 1.-typeInstructionRepository: este repositorio trea informacion consutada desde la BD asociada al modelo
 *
 */
@Setter
@Service
public class TypeInstructionService implements ITypeInstructionService{
	
	//La Constante LOGGER. Obtiene el Logger de la clase
	private static final Logger LOGGER = LoggerFactory.getLogger(TypeInstructionService.class);
	
	//Repositorio para obtener datos de typeInstructions desde BD
	@Autowired
	private ITypeInstructionRepository typeInstructionRepository;

	//Variable repositorio para tipo de instrucciones adc
	@Autowired
	private ITypeInstructionAdcRepository instructionAdcRepository;
	
	//Variable de inicio requestmapping para descarga
	private final String URI_DOWNLOAD = "/instructions/v1/type_instructions/";
	
	//Variable de fin requestmapping para descarga
	private final String URI_DOWNLOAD_METHOD = "/download";
	
	/**
	 * Este metodo realiza una consulta de tipo de instruccion por ID
	 * @param id Identificador unico de tipo de instruccion
	 * @return TipoInstruccionModel modelo de la consulta
	 */
	@Override
	public TipoInstruccionModel findById(Long id) {
		Optional<TipoInstruccionModel> tipoInstModel = this.typeInstructionRepository.findById(id);
		if(!tipoInstModel.isPresent()) {
			return null;
		}
		return tipoInstModel.get();
	}
	
	/**
	 * Este metodo permite consultar todas las tipo de instrucciones-
	 * @return List<TipoInstruccionModel> Regresa una lista de tipo de instrucciones
	 */
	@Override
	public List<TipoInstruccionModel> findAll() {
		/*Se obitenen datos del repository*/
		List<TipoInstruccionModel> listInstructionModels = typeInstructionRepository.findAll();
		/*Se filtra tipo instrucciones sin documentos
		listInstructionModels = listInstructionModels.stream()
									.filter( instr -> instr.getFlgLayou().intValue() ==0 && instr.getValAdici().intValue() == 0)
								.collect(Collectors.toList());
		*/
		return listInstructionModels;
	}

	
	/**
	 * Este metodo mapea la lista de instrucciones consultada y lo mapea a la respuesta final DTO
	 * ajustando el mapeo al modelo.
	 *@return TypeInstructionsDataResDto Regresa la respuesta DTO de acuerdo al diseño
	 */
	@Override
	public TypeInstructionsDataResDto findAllListTypInstr() {
		TypeInstructionsDataResDto listTypeInstructionsDataResDto = null;
		List<TypeInstructionDto> listTypeInstrDto = new ArrayList<>();
		
		/*Se obtiene lista todos los type Instrucciondesde*/
		List<TipoInstruccionModel> listInstructionModels = this.findAll();

		for(TipoInstruccionModel typeInst : listInstructionModels) {
		
			TypeInstructionDto tyDto = TypeInstructionDto.builder()
										.id(typeInst.getIdList().intValue())
										.name(typeInst.getDscTpoInstr())
										.codeDoc(typeInst.getCodDoc().intValue())
										.requiredLayout(false)
										.build();
			//Existe layout
			if(typeInst.getFlgLayou()!=0) {
				tyDto.setRequiredLayout(true);
			}
			
			//Se forma url para descarga de plantilla
			String urlInstr = URI_DOWNLOAD + typeInst.getIdList() + URI_DOWNLOAD_METHOD;
			tyDto.setUrlInstruction(urlInstr);
			
			//Se genera tipo de archivo por default: INSTRUCCION
			TypeInstructionsFileDto fileDtoInstruction = TypeInstructionsFileDto.builder()
																.id(FileInstruction.INSTRUCCION.getName())
																.fileName(typeInst.getDscTpoInstr())
																.required(FileRequired.TRUE.isValue())
																.extension(FileInstruction.INSTRUCCION.getFileExtension().getExtension())
																.build();		
			//Recupera lista de archivos adicionales por instruccion
			List<TipoInstruccionAdcModel> listTyInstrAdcModel = this.instructionAdcRepository.findByFkIdList(typeInst.getIdList());
			LOGGER.info("Lista archivos adc tamaño: {}",listTyInstrAdcModel.size());
			List<TypeInstructionsFileDto> listTypeIntrsFileDto =  TypeInstructionAdcMapper.toListDto(listTyInstrAdcModel, typeInst.getValAdici());
			
			//Se agrega lista de archivos adicionales (anexo, layout)
			tyDto.setFiles(listTypeIntrsFileDto);
			//Se agrega Tipo de archivo por default
			tyDto.getFiles().add(fileDtoInstruction);

			listTypeInstrDto.add(tyDto);
		}
		
		listTypeInstructionsDataResDto = TypeInstructionsDataResDto.builder()
											.data(TypeInstructionsDataDto.builder()
													.typeInstructions(listTypeInstrDto)
													.build())
											.build();
		LOGGER.debug("Tamaño lista files: {}",listTypeInstructionsDataResDto.getData().getTypeInstructions().get(0).getFiles().size());
		return listTypeInstructionsDataResDto;
	}

	/**
	 * Este metodo permite realizar la consulta de la info para descargar la plantilla
	 * de un tipo de instruccion.
	 * @param id Identificador unico del tipo de instruccion
	 * @return TypeInstrFileDownloadDto Dto con la info del archivo a descargar
	 */
	@Override
	public TypeInstrFileDownloadDto downloadDoc(Long id) {
		TypeInstrFileDownloadDto typeIntrFileDowload= null;
		
		//Se realiza la consulta de tipo de instruccion por ID
		TipoInstruccionModel tipoInstModel = this.findById(id);
		if(tipoInstModel == null) {//Valida que exista el tipo de instruccion
			//Lanza excepcion
			throw new PersistenDataException(PersistenDataCatalog.PSID003, "No se encontro tipo de instruccion a descargar, con id: "+id);
		}
		
		//Se contruye salida DTO
		typeIntrFileDowload = TypeInstrFIleDownloadMapper.toDto(tipoInstModel);
		
		if(typeIntrFileDowload.getDocBase64() == null) {//Valida que exita documento
			//Lanza excepcion
			LOGGER.error("No existe un archivo a descargar");
			throw new PersistenDataException(PersistenDataCatalog.PSID003, "No existe un archivo a descargar");
		}
		//Decodificamos el archivo
		byte docDecode[] = FileTool.decode64(typeIntrFileDowload.getDocBase64());
		//Se agrega al DTO
		typeIntrFileDowload.setDoc(docDecode);
		return typeIntrFileDowload;
	}


}