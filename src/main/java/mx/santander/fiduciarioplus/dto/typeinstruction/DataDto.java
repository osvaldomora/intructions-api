
package mx.santander.fiduciarioplus.dto.typeinstruction;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDto implements Serializable
{
	@JsonProperty("tipoInstrucciones")
    private List<TypeInstruction> typeInstruction = null;
    private final static long serialVersionUID = -6086575825938718698L;

}
