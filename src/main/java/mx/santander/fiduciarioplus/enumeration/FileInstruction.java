package mx.santander.fiduciarioplus.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Este enum permite recuperar los tipos de archivos de instrucciones
 */
@Getter
@AllArgsConstructor
public enum FileInstruction {

	INSTRUCCION("INSTRUCCION",FileExtension.PDF),
	LAYOUT("LAYOUT",FileExtension.TXT),
	ANEXO("ANEXO",FileExtension.PDF),
	PLANTILLA_DOC("PLANTILLA",FileExtension.DOC),
	PLANTILLA_DOCX("PLANTILLA",FileExtension.DOCX);
	
	private final String name;
	private final FileExtension fileExtension;
}
