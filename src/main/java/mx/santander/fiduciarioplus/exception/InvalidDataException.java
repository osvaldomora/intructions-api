package mx.santander.fiduciarioplus.exception;



import org.springframework.http.HttpStatus;

public class InvalidDataException extends GenericException{

	public InvalidDataException(HttpStatus httpStatus, String code, String message, String level, String description) {
		super(httpStatus, code, message, level, description);
	}

	private static final long serialVersionUID = 1L;

}
