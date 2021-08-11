package mx.santander.fiduciarioplus.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {BusinessException.class, InvalidDataException.class})
	public ResponseEntity<?> handlerRestException (GenericException ex, HttpServletRequest request){
		return ResponseEntity.status(ex.getHttpStatus())
									.body(ErrorResponse.builder()
											.code(ex.getCode())
											.message(ex.getMessage())
											.level(ex.getLevel())
											.description(ex.getDescription())
											.moreInfo(request.getRequestURL().toString())
											.build());

		
	}
	
}
