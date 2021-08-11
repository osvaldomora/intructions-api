package mx.santander.fiduciarioplus.model.typeinstruction;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "INSTRUCCION")
public class Instruccion implements Serializable {
	

	private static final long serialVersionUID = 5286306995658242296L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Size(min = 3, max = 100)
	@Column(name = "TIPO_DE_INSTRUCCION")
	private String name;
	
	@Column(name = "ID_DOCUMENTO")
	private String fileId;
	
	@Column(name = "NOMBRE_DOCUMENTO")
	private String fileName;
	

	@Column(name = "REQUERIDO")
	private String required;
	
	//CREO QUE NO SE VA USAR
	@Column(name = "DOCUMENTO_ADICIONAL")
	private Boolean documentoAdicional;
 

}
