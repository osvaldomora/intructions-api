package mx.santander.fiduciarioplus.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Este enum permite recuperar las extension de los archivos validos
 */
@Getter
@AllArgsConstructor
public enum FileExtension {
	PDF("pdf","pdf","type=application/pdf","application/pdf"),
	TXT("txt","txt", "type=text/plain", "text/plain"),
	DOC("doc","doc","application/msword", "application/msword"),
	DOCX("docx","docx","application/vnd.openxmlformats-officedocument.wordprocessingml.document","application/vnd.openxmlformats-officedocument.wordprocessingml.document");
	
	private final String name;
	private final String extension;
	private final String mimeType;
	private final String contentType;
	
}