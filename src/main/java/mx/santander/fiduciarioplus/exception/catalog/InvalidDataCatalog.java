package mx.santander.fiduciarioplus.exception.catalog;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvalidDataCatalog {
	//Catalogo de Errores
		ERROR001("InvalidDataException","CODIGO.001","Mensaje del error 001"),
		ERROR002("InvalidDataException","CODIGO.001","Mensaje del error 002"),
		ERROR003("InvalidDataException","CODIGO.001","Mensaje del error 003"),
		ERROR004("InvalidDataException","CODIGO.002","Mensaje del error 001");
		
		private final String type;
		private final String code;
		private final String message;
}
