package mx.santander.fiduciarioplus.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Days {
	
	DOMINGO(1, "Domingo"),
	LUNES(2, "Lunes"),
	MARTES(3, "Martes"),
	MIERCOLES(4, "Miércoles"),
	JUEVES(5, "Jueves"),
	VIERNES(6, "Viernes"),
	SABADO(7, "Sábado");
	
	private int id;
	private String name;

}