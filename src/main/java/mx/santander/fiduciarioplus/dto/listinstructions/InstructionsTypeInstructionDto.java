
package mx.santander.fiduciarioplus.dto.listinstructions;

import java.io.Serializable;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructionsTypeInstructionDto implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private boolean authorization;
	

}
