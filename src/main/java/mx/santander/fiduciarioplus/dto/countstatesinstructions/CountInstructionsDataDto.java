
package mx.santander.fiduciarioplus.dto.countstatesinstructions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.santander.fiduciarioplus.dto.listinstructions.InstructionDto;
import mx.santander.fiduciarioplus.dto.listinstructions.InstructionsDataDto;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountInstructionsDataDto implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buc;
	private Integer business;
	private Integer subBusiness;
	@Builder.Default 
	private List<CountInstructionsStatusDto> status = new ArrayList<>();
	private CountInstructionsDatesDto dates;

}
