
package mx.santander.fiduciarioplus.dto.typeinstruction;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataTypeInstructionResDto implements Serializable
{
	
    private DataDto data = null;
    private final static long serialVersionUID = -6086575825938718698L;

}
 