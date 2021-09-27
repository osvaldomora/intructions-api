package mx.santander.fiduciarioplus.dto.typeInstruction.list;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa el JSON de salida,
 * representa el objeto que incorpora toda la respuesta.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeInstructionsDataResDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private TypeInstructionsDataDto data;
}
