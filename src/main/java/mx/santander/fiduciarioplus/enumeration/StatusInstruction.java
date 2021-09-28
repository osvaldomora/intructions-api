package mx.santander.fiduciarioplus.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusInstruction {

	SOLICITADA("SO", "SOLICITADA",1L),
	ENTREGADA("EN", "ENTREGADA",2L),
	PROCESO("PR", "EN PROCESO",3L),
	ATENDIDA("AT", "ATENDIDA",4L),
	RECHAZADA("RE", "RECHAZADA",5L);
	
	private String id;
	private String description;
	private Long value;
}
