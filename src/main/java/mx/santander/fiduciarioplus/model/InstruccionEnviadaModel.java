package mx.santander.fiduciarioplus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "FID_MX_REL_INSTR_NVAS")
public class InstruccionEnviadaModel {

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSTR_NVAS_SEQ")
    //@SequenceGenerator(sequenceName = "SEQFID_MX_REL_INSTR_NVAS", allocationSize = 1, name = "INSTR_NVAS_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INSTR_NVAS")
	@NotNull
	private Long idIntrsNvas;
	
	@Column(name = "FK_ID_LIST")
	private Long idList;
	
	@Column(name = "FK_ID_BUC")
	private String buc;
	
	@Column(name = "ID_NO_CONTR")
	private Long idNoContr;
	
	@Column(name = "ID_NO_SUBCONTR")
	private Long idNoSubContr;
	
	@Column(name = "ID_FOLIO")
	private Long IdFolio;
	
	@Column(name = "FK_ID_ESTAT")
	private Long idEstat;
	
	@Column(name = "FCH_REGIS_INSCT")
	private Date fchRegisInsct;
	
}
