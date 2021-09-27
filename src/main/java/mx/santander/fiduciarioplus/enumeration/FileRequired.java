package mx.santander.fiduciarioplus.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *Este enum permite saber si un archivo es requerido
 */
@Getter
@AllArgsConstructor
public enum FileRequired {

	TRUE("TRUE",true),
	OPCIONAL("OPTIONAL",false);
	
	private final String name;
	private final boolean value;
}
