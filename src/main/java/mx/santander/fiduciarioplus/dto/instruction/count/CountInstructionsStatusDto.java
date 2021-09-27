
package mx.santander.fiduciarioplus.dto.instruction.count;

import java.io.Serializable;

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
public class CountInstructionsStatusDto implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String description;
	private Integer quantity;

}
