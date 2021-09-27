package mx.santander.fiduciarioplus.service;

import java.util.List;

import mx.santander.fiduciarioplus.model.TipoInstruccionAdcModel;


/**
 * Interfaz publica para las operaciones de negocio de la entidad TipoInstruccionAdcModel
 */
public interface ITypeInstructionAdcService {
	
	/**
	 * Consulta una lista de tipo de instruccion adc por id
	 * @param id identificador del tipo de instruccion
	 * @return List<TipoInstruccionAdcModel> lista de instrucciones adc
	 */
	List<TipoInstruccionAdcModel> findByFkIdList(Long idTypeInstruction);
	
	TipoInstruccionAdcModel findByIdTypeInstruc(Long findByFkIdList);

}
