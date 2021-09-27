package mx.santander.fiduciarioplus.exception.catalog;

import org.springframework.http.HttpStatus;

/**
 * Este ENUM define el catalog de errores de la categoria General
 * Esta clase permite la enumeracion de diferentes mensajes de excepcion
 * utilizados en los cuerpos de respuesta HTTP arrojados por el servicio
 * Es posible agregar nuevos mensajes personalizados
 * para permitir que el servicio sea mas explicito,
 * recordando siempre que es importante evitar arrojar informacion sensible.
 */
public enum GeneralCatalog {
	
	GRAL001("GeneralException001","GRAL.001","Error generico de servidor.",HttpStatus.INTERNAL_SERVER_ERROR,LevelException.ERROR),
	GRAL002("GeneralException002","GRAL.002","Error al enviar Query Parameters.",HttpStatus.BAD_REQUEST,LevelException.WARN),
	GRAL003("GeneralException003","GRAL.003","URI parameters no valido.",HttpStatus.BAD_REQUEST,LevelException.WARN),
	GRAL004("GeneralException004","GRAL.004","Conflicto al realizar la operación.",HttpStatus.CONFLICT,LevelException.ERROR),
	GRAL005("GeneralException005","GRAL.005","Consulta duplicada.",HttpStatus.BAD_REQUEST,LevelException.WARN),
	GRAL006("GeneralException006","GRAL.006","Error al enviar parametros.",HttpStatus.BAD_REQUEST,LevelException.WARN)	;
	
	private final String type;
	private final String code;
	private final String message;
	private final HttpStatus htttpStatus;
	private final LevelException levelException;
	
	GeneralCatalog(String type, String code, String message, HttpStatus htttpStatus,
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