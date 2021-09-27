package mx.santander.fiduciarioplus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "FID_MX_MAE_LIST_INSTR_ADC")
public class TipoInstruccionAdcModel {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_LIST_ADICI")
	@NotNull
	private Long idListAdici;
	
	@Column(name = "FK_ID_LIST")
	@Size(min = 0, max = 3, message = "Cantidad minima y maxima de digitos entre 0 a 3")
	@NotEmpty
	private Long fkIdList;
	
	@Column(name = "DSC_NOMBR")
	@Size(min =0, max = 200, message = "Cantidad minima y maxima de caracteres de 0 a 200")
	private String dscNombr;
	
	@Column(name = "DSC_FRMTO")
	@Size(min =0, max = 50, message = "Cantidad minima y maxima de caracteres de 0 a 50")
	private String dscFrmto;
	
	@Column(name = "FLG_LAYOU")
	@Size(min =0, max = 1, message = "Cantidad minima y maxima de digitos entre 0 a 1")
	private Integer flgLayou;

}
