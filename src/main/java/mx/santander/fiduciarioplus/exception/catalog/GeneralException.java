package mx.santander.fiduciarioplus.exception.catalog;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GeneralException {
	
	GRAL001("BusinessException","GRAL.001","Error al enviar Query Parameters");
	
	private final String type;
	private final String code;
	private final String message;
}
