package mx.santander.fiduciarioplus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "FID_MX_MAE_LIST_INSTR")
public class TipoInstruccionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_LIST", nullable = false)
	@Size(max = 3, message = "Cantidad maxima de digitos 3")
	@NotNull
	private Long idList;
	
	@Column(name = "DSC_TPO_INSTR")
	@Size(min =0, max = 200, message = "Cantidad minima y maxima de caracteres de 0 a 200")
	private String dscTpoInstr;
	
	@Column(name = "DSC_NOMBR")
	@Size(min =0, max = 200, message = "Cantidad minima y maxima de caracteres de 0 a 200")
	private String dscNombr;
	
	@Column(name = "COD_DOC")
	@Size(max = 4, message = "Cantididad maxima de digitos 4")
	private Long codDoc;
	
	@Column(name = "DSC_FRMTO")
	@Size(min =0, max = 10, message = "Cantidad minima y maxima de caracteres de 0 a 10")
	private String dscFrmto;
	
	@Column(name = "FLG_LAYOU")
	@Size(max = 1, message = "Cantididad maxima de digitos 1")
	private Long flgLayou;
	
	@Column(name = "VAL_ADICI")
	@Size(max = 1, message = "Cantididad maxima de digitos 1")
	private Long valAdici;
	
	@Column(name = "VAL_INSTR")
	@Lob
	private String valInstr;
	
}
