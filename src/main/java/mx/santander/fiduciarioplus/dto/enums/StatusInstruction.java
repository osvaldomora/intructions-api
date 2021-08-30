package mx.santander.fiduciarioplus.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusInstruction {

	SOLICITADA("SO"),
	ENTREGADA("EN"),
	PROCESO("PR"),
	ATENDIDA("AT"),
	RECHAZADA("RE");
	
	private String id;
}
