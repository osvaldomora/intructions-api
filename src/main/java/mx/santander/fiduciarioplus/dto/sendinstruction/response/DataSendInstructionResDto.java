
package mx.santander.fiduciarioplus.dto.sendinstruction.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataSendInstructionResDto implements Serializable{

	public DataDto data;
	private static final long serialVersionUID = 1L;
}
