package mx.santander.fiduciarioplus.exception.catalog;

import org.springframework.http.HttpStatus;

/**
 * Este ENUM define el catalog de errores de la categoria Business
 * Esta clase permite la enumeracion de diferentes mensajes de excepcion
 * utilizados en los cuerpos de respuesta HTTP arrojados por el servicio
 * Es posible agregar nuevos mensajes personalizados
 * para permitir que el servicio sea mas explicito,
 * recordando siempre que es importante evitar arrojar informacion sensible.
 */
public enum BusinessCatalog {
	
	BUSI001("BusinessException001","BUSI.001","Error el documento no puede exceder el minimo y maximo de tamaño.",HttpStatus.BAD_REQUEST,LevelException.WARN),
	BUSI002("BusinessException002","BUSI.002","Formato de archivo no soportado.",HttpStatus.BAD_REQUEST,LevelException.WARN),
	BUSI003("BusinessException003","BUSI.003","Se ha excedido el limite de archivos.",HttpStatus.BAD_REQUEST,LevelException.WARN);

	private final String type;
	private final String code;
	private final String message;
	private final HttpStatus htttpStatus;
	private final LevelException levelException;
	
	BusinessCatalog(String type, String code, String message, HttpStatus htttpStatus,
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