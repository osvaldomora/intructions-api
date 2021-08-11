package mx.santander.fiduciarioplus.exception.catalog;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersistentDataCatalog {

	PSID001("BusinessException","PSID.001","Error al realizar la transaccion."),
	PSID002("BusinessException","PSID.002","Error de conexion.");
	
	private final String type;
	private final String code;
	private final String message;
}
