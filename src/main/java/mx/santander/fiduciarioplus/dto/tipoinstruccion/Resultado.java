
package mx.santander.fiduciarioplus.dto.tipoinstruccion;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resultado implements Serializable
{

    public Integer codigo;
    public String info;
    private final static long serialVersionUID = 8952891093537955279L;

}
