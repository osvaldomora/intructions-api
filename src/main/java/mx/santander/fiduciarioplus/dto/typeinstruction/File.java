
package mx.santander.fiduciarioplus.dto.typeinstruction;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.santander.fiduciarioplus.dto.enums.Extension;
import mx.santander.fiduciarioplus.dto.enums.Id;
import mx.santander.fiduciarioplus.dto.enums.Required;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class File implements Serializable
{
	

	private static final long serialVersionUID = -6655732514322381748L;
	private Id id_documento;
    private String nombre_documento;
    private  Required requerido ;
    private Extension extension;
    

}
