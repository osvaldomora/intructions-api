package mx.santander.fiduciarioplus.dto.instruction.count;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class CountInstructionStatusPerDay implements Serializable{

	private static final long serialVersionUID = 1L;
	@Builder.Default 
	private List<CountInstructionsStatusDto> status = new ArrayList<>();
	private Integer totalStatus;
	private Date date;
	private String day;
}
