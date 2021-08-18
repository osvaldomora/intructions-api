package mx.santander.fiduciarioplus.model.typeinstruction;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TYPE_INSTRUCCION")
public class Instruccion implements Serializable {
	

	private static final long serialVersionUID = 5286306995658242296L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	
	@Column(name = "URL_INSTRUCTION")
	private String urlInstruction;
	
	@Size(min = 3, max = 100)
	@Column(name = "TIPO_DE_INSTRUCCION")
	private String name;
	
	@Column(name = "NOMBRE_DOCUMENTO")
	private String fileName;
	
	@Column(name = "ID_DOCUMENTO")
	private String fileId;
	
//	@Column(name = "REQUERIDO")
//	private String required;
	

 

}
