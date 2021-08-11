package mx.santander.fiduciarioplus.exception.catalog;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvalidDataCatalog {
	//Catalogo de Errores
		INVD001("InvalidDataException","INVD.001","Error al mapear datos entrada/salida."),
		INVD002("InvalidDataException","INVD.002","Error al codificar archivo.");
	
		private final String type;
		private final String code;
		private final String message;
}
