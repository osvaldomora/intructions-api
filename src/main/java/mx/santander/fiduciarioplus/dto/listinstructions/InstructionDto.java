
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
public class InstructionDto implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InstructionBusinessDto business;
	private SubBusinessDto subBusiness;
	private InstructionsFileDto file;
	private InstructionsTypeInstructionDto typeInstruction;
	private StatusDto status;
	private String date;

}
