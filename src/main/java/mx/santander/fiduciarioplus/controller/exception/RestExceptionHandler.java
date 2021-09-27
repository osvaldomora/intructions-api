package mx.santander.fiduciarioplus.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import mx.santander.fiduciarioplus.controller.exception.model.DefaultError;
import mx.santander.fiduciarioplus.controller.exception.model.DefaultErrorList;
import mx.santander.fiduciarioplus.controller.exception.model.ModelException;
import mx.santander.fiduciarioplus.exception.BusinessException;
import mx.santander.fiduciarioplus.exception.GeneralException;
import mx.santander.fiduciarioplus.exception.InvalidDataException;
import mx.santander.fiduciarioplus.exception.PersistenDataException;
import mx.santander.fiduciarioplus.exception.catalog.BusinessCatalog;
import mx.santander.fiduciarioplus.exception.catalog.GeneralCatalog;


/**
 * @author David Gonzalez - (Arquetipo creado por Santander Tecnologia Mexico)
 * 
 * Esta clase se encarga de servir como apoyo al controller, manejando de manera desacoplada 
 * las excepciones esperadas en la aplicacion, y manejando el catalogo de errores con ayuda de un enumerador personalizado.
 * Tambien tiene un manejo de errores genericos.
 */
@ControllerAdvice
public class RestExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	/**
	 * Manejo de excepciones personalizadas de acuerdo al negocio
	 * @param ex Modelo de la excepcion recibida
	 * @param request Informacion del request enviado
	 * @return Respuesta basada en el catalogo de excepciones
	 */
	@ExceptionHandler({BusinessException.class, InvalidDataException.class, PersistenDataException.class,GeneralException.class})
	public ResponseEntity<DefaultErrorList> handlerRestException (ModelException ex, HttpServletRequest request){
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList
														(new DefaultError
																(ex.getCode(), 
																 ex.getMessage(),
																 ex.getLevel(),
																 ex.getDescription(),
																 request.getRequestURL().toString())
																),ex.getHttpStatus());
	}
    
    /**
     * Manejo de excepciones de validacion de argumentos de entrada
     * @param pe Excepcion de tipo MethodArgumentNotValidException
     * @return La entidad de respuesta que maneja el error como objeto
     */
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<DefaultErrorList> handleValidationExceptionA(MethodArgumentNotValidException pe, HttpServletRequest request) {
    	LOGGER.warn("Argmentos invalidos, {}", pe.getMessage());
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(GeneralCatalog.GRAL006.getCode(),
																							GeneralCatalog.GRAL006.getMessage(),
																							GeneralCatalog.GRAL006.getLevelException().toString(),
																							pe.getBindingResult().getFieldError().getField().toString() + ": " + pe.getBindingResult().getFieldError().getDefaultMessage(),
																							request.getRequestURL().toString()
																							)), GeneralCatalog.GRAL006.getHtttpStatus());
	}

	/**
     * Manejo de excepciones de validacion de formatos de numeros de entrada
     * @param pe Excepcion de tipo NumberFormatException
     * @return La entidad de respuesta que maneja el error como objeto
     */
	@ExceptionHandler(value = {NumberFormatException.class})
	public ResponseEntity<DefaultErrorList> handleValidationExceptionB(NumberFormatException pe, HttpServletRequest request) {
    	LOGGER.warn("Excepcion de formatos de numeros de entrada, {}", pe.getMessage());
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(GeneralCatalog.GRAL002.getCode(),
																						GeneralCatalog.GRAL002.getMessage(),
																						GeneralCatalog.GRAL002.getLevelException().toString(),
																						"Excepcion de formatos de numeros de entrada",
																						request.getRequestURL().toString()
																						)), GeneralCatalog.GRAL002.getHtttpStatus());
	}

	/**
     * Manejo de excepciones de validacion de tipo de datos de entrada
     * @param pe Excepcion de tipo MethodArgumentTypeMismatchException
     * @return La entidad de respuesta que maneja el error como objeto
     */
	@ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
	public ResponseEntity<DefaultErrorList> handleValidationExceptionC(MethodArgumentTypeMismatchException pe, HttpServletRequest request) {
    	LOGGER.warn("Excepcion de tipo de datos de entrada, {}", pe.getMessage());
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(GeneralCatalog.GRAL002.getCode(),
																							GeneralCatalog.GRAL002.getMessage(),
																							GeneralCatalog.GRAL002.getLevelException().toString(),
																							"Excepcion de tipo de datos de entrada",
																							request.getRequestURL().toString()
																							)), GeneralCatalog.GRAL002.getHtttpStatus());
	}


	/**
     * Manejo de excepciones de validacion de cantidad de parametros de entrada enviados
     * @param pe Excepcion de tipo MissingServletRequestParameterException
     * @return La entidad de respuesta que maneja el error como objeto
     */
	@ExceptionHandler(value = {MissingServletRequestParameterException.class})
	public ResponseEntity<DefaultErrorList> handleValidationExceptionD(MissingServletRequestParameterException pe, HttpServletRequest request) {
    	LOGGER.warn("Excepcion de cantidad de parametros de entrada enviados, {}", pe.getMessage());
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError(GeneralCatalog.GRAL002.getCode(),
																							GeneralCatalog.GRAL002.getMessage(),
																							GeneralCatalog.GRAL002.getLevelException().toString(),
																							"Excepcion de cantidad de parametros de entrada enviados",
																							request.getRequestURL().toString()
																							)), GeneralCatalog.GRAL002.getHtttpStatus());
	}
	
    
	/**
	 * @param ex Modelo de la excepcion recibida, controla excepcion de tamanio de archivo
	 * @param request Informacion del request enviado
	 * @return Respuesta basada en el catalogo de excepciones
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<DefaultErrorList> handleExceptionSizeFile(MaxUploadSizeExceededException ex,  HttpServletRequest request) {
		LOGGER.warn("El documento no puede exceder el limite del documento, {}", ex.getMessage());
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError
																			(BusinessCatalog.BUSI001.getCode(),
																			BusinessCatalog.BUSI001.getMessage(),
																			BusinessCatalog.BUSI001.getLevelException().toString(),
															  				"El documento no puede exceder el limite del documento",
																			request.getRequestURL().toString()
																			 )), BusinessCatalog.BUSI001.getHtttpStatus());
	}
	
	/**
	 * Manejo de excepcion metodo HTTP no soportado
	 * @param ex Excepcion generica de tipo Exception
	 * @return La entidad de respuesta que maneja el error como objeto
	 */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<DefaultErrorList> handlerMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
		LOGGER.error("Error metodo HTTP no soportado para este endpoint, {}", request.getRequestURL().toString());
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError
																			(GeneralCatalog.GRAL001.getCode(),
																			GeneralCatalog.GRAL001.getMessage(),
																			GeneralCatalog.GRAL001.getLevelException().toString(),
																			"Metodo HTTP no soportado para este endpoint",
																			request.getRequestURL().toString()
																			)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
    
	/**
	 * Manejo de excepcion generica
	 * @param ex Excepcion generica de tipo Exception
	 * @return La entidad de respuesta que maneja el error como objeto
	 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DefaultErrorList> handleGenericException(Exception ex, HttpServletRequest request) {
		LOGGER.error("Error en la ejecucion: {}", ex.getMessage());
		return new ResponseEntity<DefaultErrorList>(new DefaultErrorList(new DefaultError
																			(GeneralCatalog.GRAL001.getCode(),
																			GeneralCatalog.GRAL001.getMessage(),
																			GeneralCatalog.GRAL001.getLevelException().toString(),
																			request.getRequestURL().toString()
																			)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}