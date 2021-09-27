package mx.santander.fiduciarioplus.dto.typeInstruction.download;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase represnta la info para poder descargar el archivo,
 * de un tipo de instruccion.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeInstrFileDownloadDto implements Serializable{
	//Variable de serializable
	private static final long serialVersionUID = 1L;
	private String name;
	private String extension;
	private String fullName;
	private String docBase64;
	private byte[] doc;
	private String mimeType;
}
