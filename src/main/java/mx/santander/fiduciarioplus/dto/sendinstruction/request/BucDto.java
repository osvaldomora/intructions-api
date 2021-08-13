package mx.santander.fiduciarioplus.dto.sendinstruction.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BucDto implements Serializable{

	private String id;
    private String token;

	private static final long serialVersionUID = 1L;
}
