
package  mx.santander.fiduciarioplus.dto.sendinstruction.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDto implements Serializable{

	public FolioDto folio;
	private static final long serialVersionUID = 1L;

}
