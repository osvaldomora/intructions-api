
package mx.santander.fiduciarioplus.dto.listinstructions;

import java.io.Serializable;
import java.util.Date;

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
	private String folio;
	private InstructionBusinessDto business;
	private SubBusinessDto subBusiness;
	private InstructionsFileDto file;
	private InstructionsTypeInstructionDto typeInstruction;
	private StatusDto status;
	private Date date;

}
