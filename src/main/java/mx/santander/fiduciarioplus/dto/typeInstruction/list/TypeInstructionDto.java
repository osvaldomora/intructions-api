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
 * es el cuerpo principal de la respuesta, representa la info de la instruccion.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeInstructionDto implements Serializable{
	//Variable de serializable
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
    private Integer codeDoc;
    private String urlInstruction;
    private boolean requiredLayout;
    @Builder.Default
    private List<TypeInstructionsFileDto> files = new ArrayList<>();
}
