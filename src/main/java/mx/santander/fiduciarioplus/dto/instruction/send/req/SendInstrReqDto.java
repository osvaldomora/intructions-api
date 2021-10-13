
package mx.santander.fiduciarioplus.dto.instruction.send.req;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

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
public class SendInstrReqDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean committee;
	private SendIntrsBucDto buc;
    private SendInstrTypeInstructionDto typeInstruction;
    private SendInstrBusinessDto business;
    @Builder.Default
    private List<SendInstrFileDto> files = new ArrayList<>();

}
