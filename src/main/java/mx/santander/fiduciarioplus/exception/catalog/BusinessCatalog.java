package mx.santander.fiduciarioplus.exception.catalog;



import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessCatalog{

	//Catalogo de Errores
	BUSI001("BusinessException","BUSI.001","Error el documento no puede exceder el minimo y maximo de tamaño."),
	BUSI002("BusinessException","BUSI.002","Formato de archivo no soportado."),
	BUSI003("BusinessException","BUSI.003","Se ha excedido el limite de archivos.");
	
	private final String type;
	private final String code;
	private final String message;

}
