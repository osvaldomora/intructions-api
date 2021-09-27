package mx.santander.fiduciarioplus.dto.instruction.count;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class CountInstructionsDataDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String buc;
	private Long business;
	private Long subBusiness;
	@Builder.Default 
	private List<CountInstructionStatusPerDay> statusPerDay = new ArrayList<>();
	private CountInstructionsDatesDto dates;

}
