package mx.santander.fiduciarioplus.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusInstruction {

	SOLICITADA("SO", "SOLICITADA"),
	ENTREGADA("EN", "ENTREGADA"),
	PROCESO("PR", "EN PROCESO"),
	ATENDIDA("AT", "ATENDIDA"),
	RECHAZADA("RE", "RECHAZADA");
	
	private String id;
	private String description;
}
