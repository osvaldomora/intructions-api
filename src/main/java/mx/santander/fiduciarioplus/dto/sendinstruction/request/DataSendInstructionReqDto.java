
package mx.santander.fiduciarioplus.dto.sendinstruction.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataSendInstructionReqDto implements Serializable{

    public BucDto buc;
    public String date;
    public TypeInstructionDto typeInstruction;
    public BusinessDto business;

	private static final long serialVersionUID = 1L;
}
