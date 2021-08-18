package mx.santander.fiduciarioplus.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mx.santander.fiduciarioplus.exception.catalog.BusinessCatalog;
import mx.santander.fiduciarioplus.exception.catalog.GeneralCatalog;
import mx.santander.fiduciarioplus.exception.model.BusinessException;
import mx.santander.fiduciarioplus.exception.model.InvalidDataException;
import mx.santander.fiduciarioplus.exception.model.ModelException;
import mx.santander.fiduciarioplus.exception.model.PersistenDataException;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


	/**
	 * @param ex Modelo de la excepcion recibida
	 * @param request Informacion del request enviado
	 * @return Respuesta basada en el catalogo de excepciones
	 */
	@ExceptionHandler({BusinessException.class, InvalidDataException.class, PersistenDataException.class})
	public ResponseEntity<?> handlerRestException (ModelException ex, HttpServletRequest request){
		ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), 
				ex.getMessage(), 
				ex.getLevel(), 
				ex.getDescription(), 
				request.getRequestURL().toString());	
		return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);

		
	}
	
	/**
	 * @param ex Modelo de la excepcion recibida, controla excepcion de tamanio de archivo
	 * @param request Informacion del request enviado
	 * @return Respuesta basada en el catalogo de excepciones
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<?> handleExceptionSizeFile(Exception ex,  HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(BusinessCatalog.BUSI001.getCode(), 
				BusinessCatalog.BUSI001.getMessage(), 
				BusinessCatalog.BUSI001.getLevelException().toString(), 
				request.getRequestURL().toString());	
		return ResponseEntity.status(BusinessCatalog.BUSI001.getHtttpStatus()).body(errorResponse);
	}
	
	/**
	 * @param ex Modelo de la excepcion recibida, controla excepciones al no enviar parametros
	 * @param request Informacion del request enviado
	 * @return Respuesta basada en el catalogo de excepciones
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(GeneralCatalog.GRAL002.getCode(), 
				GeneralCatalog.GRAL002.getMessage(), 
				GeneralCatalog.GRAL002.getLevelException().toString(), 
				((ServletWebRequest)request).getRequest().getRequestURI());	
		return ResponseEntity.status(GeneralCatalog.GRAL002.getHtttpStatus()).body(errorResponse);
	}
	
	/**
	 * @param ex Modelo de la excepcion recibida, controla excepciones generales
	 * @param request Informacion del request enviado
	 * @return Respuesta basada en el catalogo de excepciones
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleExceptionGeneric(Exception ex,  HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(GeneralCatalog.GRAL001.getCode(), 
				GeneralCatalog.GRAL001.getMessage(), 
				GeneralCatalog.GRAL001.getLevelException().toString(),
				ex.getMessage(), //Error desconocido
				request.getRequestURL().toString());
		return ResponseEntity.status(GeneralCatalog.GRAL001.getHtttpStatus()).body(errorResponse);
	}



}
