package mx.santander.fiduciarioplus.exception.catalog;



import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessCatalog{

	//Catalogo de Errores
	ERROR001("BusinessException","CODIGO.001","Mensaje del error 001"),
	ERROR002("BusinessException","CODIGO.001","Mensaje del error 002"),
	ERROR003("BusinessException","CODIGO.001","Mensaje del error 003"),
	ERROR004("BusinessException","CODIGO.002","Mensaje del error 001"),
	ERROR005("BusinessException","CODIGO.003","Mensaje del error 001"),
	ERROR006("BusinessException","CODIGO.004","Mensaje del error 001"),
	ERROR007("BusinessException","CODIGO.004","Mensaje del error 004");

	private final String type;
	private final String code;
	private final String message;

}
