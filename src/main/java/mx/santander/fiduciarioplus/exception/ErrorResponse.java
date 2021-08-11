package mx.santander.fiduciarioplus.exception;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

	private String code;
	private String message;
	private String level;
	private String description;
	private String moreInfo;
	
}
