
package mx.santander.fiduciarioplus.dto.tipoinstruccion;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Archivo implements Serializable
{
	

	private static final long serialVersionUID = -6655732514322381748L;
	private String id_documento;
    private String nombre_documento;
    private  Requerido requerido ;
    private Extension extension;
    

}
