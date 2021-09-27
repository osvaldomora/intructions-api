package mx.santander.fiduciarioplus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class InstruccionEnviada {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_INSTR_NVAS")
	@NotNull
	private Long idIntrsNvas;
	
	@OneToOne
	@JoinColumn(name = "FK_ID_LIST", referencedColumnName = "ID_LIST")
	private TipoInstruccionModel tipoInstr;
	
	@Column(name = "FK_ID_BUC")
	private String idFkBuc;
	
	@Column(name = "ID_NO_CONTR")
	private Long idNoContr;
	
	@Column(name = "ID_NO_SUBCONTR")
	private Long idNoSubContr;
	
	@Column(name = "ID_FOLIO")
	private Long idFolio;
	
	@OneToOne
	@JoinColumn(name = "FK_ID_ESTAT", referencedColumnName = "ID_ESTAT")
	private EstatusInstruccion estatusInstr;
	
	@Column(name = "FCH_REGIS_INSCT")
	private Date fchRegisInsct;
	
}
