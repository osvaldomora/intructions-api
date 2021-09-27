package mx.santander.fiduciarioplus.dto.typeInstruction.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa el JSON de salida,
 * estructura inicial de la salida,
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeInstructionsDataDto implements Serializable{
	
	//Variable de serializable
	private static final long serialVersionUID = 1L;
	//Variable de tipo de instrucciones
	@Builder.Default
	private List<TypeInstructionDto> typeInstructions = new ArrayList<>();
}
