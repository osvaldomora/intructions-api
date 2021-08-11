package mx.santander.fiduciarioplus.exception.catalog;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersistentDataCatalog {

	PSID001("PersistentException","PSID.001","Error al realizar la transaccion."),
	PSID002("PersistentException","PSID.002","Error de conexion.");
	
	private final String type;
	private final String code;
	private final String message;
}
