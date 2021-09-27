package mx.santander.fiduciarioplus.dto.instruction.count;

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
public class CountInstructionsDatesDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Date start;
    private Date end;

}
