package mx.santander.fiduciarioplus.model.tipoinstruccion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	private String id_documento;
	
	@Column(name = "NOMBRE_DOCUMENTO")
	private String nombre_documento;
	

	@Column(name = "REQUERIDO")
	private String requerido;
	
	//CREO QUE NO SE VA USAR
	@Column(name = "DOCUMENTO_ADICIONAL")
	private Boolean documentoAdicional;
 

}
