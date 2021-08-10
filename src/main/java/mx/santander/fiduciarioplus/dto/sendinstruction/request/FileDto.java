package mx.santander.fiduciarioplus.dto.sendinstruction.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDto {

	private String fileFullName;
	private String fileName;
	private String extension;
	private Long size;
}
