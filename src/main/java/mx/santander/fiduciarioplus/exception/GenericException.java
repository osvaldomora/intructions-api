package mx.santander.fiduciarioplus.exception;


import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GenericException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
	private final String level;
	private final String description;
	private String moreInfo;
	
	

}
