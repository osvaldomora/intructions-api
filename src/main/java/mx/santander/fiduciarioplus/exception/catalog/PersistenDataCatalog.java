package mx.santander.fiduciarioplus.exception.catalog;

import org.springframework.http.HttpStatus;

/**
 * Este ENUM define el catalog de errores de la categoria Persistent
 * Esta clase permite la enumeracion de diferentes mensajes de excepcion
 * utilizados en los cuerpos de respuesta HTTP arrojados por el servicio
 * Es posible agregar nuevos mensajes personalizados
 * para permitir que el servicio sea mas explicito,
 * recordando siempre que es importante evitar arrojar informacion sensible.
 */
public enum PersistenDataCatalog {
	
	PSID001("PersistentDataException001","PSID.001","Error al realizar la transaccion.",HttpStatus.CONFLICT,LevelException.ERROR),
	PSID002("PersistentDataException002","PSID.002","Error de conexion en origen de datos.",HttpStatus.CONFLICT,LevelException.ERROR),
	PSID003("PersistentDataException003","PSID.003","Recurso no encontrado.",HttpStatus.CONFLICT,LevelException.ERROR);
	
	private final String type;
	private final String code;
	private final String message;
	private final HttpStatus htttpStatus;
	private final LevelException levelException;
	
	PersistenDataCatalog(String type, String code, String message, HttpStatus htttpStatus,
			LevelException levelException) {
		this.type = type;
		this.code = code;
		this.message = message;
		this.htttpStatus = htttpStatus;
		this.levelException = levelException;
	}
	public String getType() {
		return type;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public HttpStatus getHtttpStatus() {
		return htttpStatus;
	}
	public LevelException getLevelException() {
		return levelException;
	}

}