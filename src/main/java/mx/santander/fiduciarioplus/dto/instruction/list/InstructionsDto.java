package mx.santander.fiduciarioplus.dto.instruction.list;

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
public class InstructionsDto implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String folio;
	private InstructionsBusinessDto business;
	private InstructionsSubBusinessDto subBusiness;
	private InstructionsFileDto file;
	private InstructionsTypeInstructionDto typeInstruction;
	private InstructionsStatusDto status;
	private Date createdAt;

}
