package mx.santander.fiduciarioplus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "FID_MX_REL_INSTR_ANEXO")
public class InstruccionAnexoModel {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSTR_ANEXO_SEQ")
    //@SequenceGenerator(sequenceName = "SEQFID_MX_REL_INSTR_ANEXO", allocationSize = 1, name = "INSTR_ANEXO_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ANEXO")
	@NotNull
	private Long idAnexo;
	
	@Column(name = "FK_ID_INSTR_NVAS")
	private Long idFkInstrNvas;
	
	@Column(name = "DSC_DOC_NOMBR")
	private String dscDocNombr;
	
	@Column(name = "DSC_TIPO")
	private String dsc_Tipo;
	
	@Column(name = "ID_FOLIO")
	private Long idFolio;
	
	@Column(name = "FCH_REGIS_INSCT")
	private Date fchRegisInst;
	
	@Column(name = "VAL_INSTR")
	@Lob
	private String valInstr;

}
