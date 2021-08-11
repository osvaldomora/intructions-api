package mx.santander.fiduciarioplus.model.sendinstruction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "SEND_FILE")
public class SendFIle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FILE")
	private Long idFile;
	
	@Column(name = "FULL_NAME_FILE")
	private String fileFullName;
	
	@Column(name = "NAME_FILE")
	private String fileName;
	
	@Column(name = "EXTENSION_FILE")
	private String extension;
	
	@Column(name = "SIZE_BYTES_FILE")
	private Long size;
	
	@Column(name = "FILE_BASE_64")
	@Lob	//Notacion para H2 y guardar un LONG TEXT
	private String fileBase64;
}
