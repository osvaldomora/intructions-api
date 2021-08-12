
package mx.santander.fiduciarioplus.dto.typeinstruction;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.santander.fiduciarioplus.dto.enums.ExtensionFile;
import mx.santander.fiduciarioplus.dto.enums.IdFile;
import mx.santander.fiduciarioplus.dto.enums.RequiredFile;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDto implements Serializable
{
	

	private static final long serialVersionUID = -6655732514322381748L;
	private IdFile fileId;
    private String fileName;
    private  RequiredFile requiredFile ;
    private ExtensionFile extensionFile;
    

}
