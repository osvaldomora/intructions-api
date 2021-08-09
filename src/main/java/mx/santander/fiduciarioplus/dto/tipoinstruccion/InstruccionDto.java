
package mx.santander.fiduciarioplus.dto.tipoinstruccion;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstruccionDto implements Serializable {

	private Resultado resultado;
	private Dato dato;
	private final static long serialVersionUID = -5724389527430682191L;

}
