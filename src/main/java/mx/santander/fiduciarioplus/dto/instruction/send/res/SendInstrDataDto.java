package mx.santander.fiduciarioplus.dto.instruction.send.res;

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
public class SendInstrDataDto implements Serializable{

	private static final long serialVersionUID = 1L;
	@Builder.Default
	public List<SendIntrsFolioDto> folios = new ArrayList<>();

}
