package mx.santander.fiduciarioplus.dto.instruction.send.req;

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
public class SendInstrBusinessDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
    private SendInstrSubBusinessDto subBusiness;

}
