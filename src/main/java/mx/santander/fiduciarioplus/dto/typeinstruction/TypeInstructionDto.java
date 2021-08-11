
package mx.santander.fiduciarioplus.dto.typeinstruction;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeInstructionDto implements Serializable
{

    public Long id;
    public String name;
    public List<FileDto> files = null;
    private final static long serialVersionUID = -3651510353810542647L;

}
