
package mx.santander.fiduciarioplus.dto.countstatesinstructions;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class CountInstructionsDatesDto implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date start;
    private Date end;

}
