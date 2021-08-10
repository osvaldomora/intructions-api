
package mx.santander.fiduciarioplus.dto.sendinstruction.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeInstructionDto implements Serializable{

    public String id;
    public String name;
    
	private static final long serialVersionUID = 1L;

}
