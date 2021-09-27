package mx.santander.fiduciarioplus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "FID_MX_MAE_CAT_ESTAT_INSTR")
public class EstatusInstruccion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_ESTAT", nullable = false)
	@NotNull
	private Long idEstat;
	
	@Column(name = "DSC_NOMBR")
	private String dscNombr;
	
}
