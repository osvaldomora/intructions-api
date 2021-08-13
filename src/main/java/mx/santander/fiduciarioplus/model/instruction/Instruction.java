package mx.santander.fiduciarioplus.model.instruction;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "INSTRUCTION")
public class Instruction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INSTRUCTION")
	private Long idInstruction;
	
	@Column(name = "BUC")
	private String buc;
	
	@Column(name = "TOKEN_BUC")
	private String token;
	
	@Column(name = "DATE")
	private Date date;
	
	@Column(name = "ID_TYPE_INSTRUCTION")
	private String idTypeInstruction;
	
	@Column(name = "NAME_INSTRUCTION")
	private String nameInstruction;
	
	@Column(name = "ID_BUSINESS")
	private String idBusiness;
	
	@Column(name = "ID_SUB_BUSINESS")
	private String idSubBusiness;
	
	@Column(name = "STATUS_INSTRUCTION")
	private String status;
	
	@Column(name = "CAUSE_STATUS")
	private String statusCause;
	
	@Column(name = "FILE_URL")
	private String fileUrl;
	
	@Column(name = "ID_FOLIO")
	private String idFolio;
	
}
