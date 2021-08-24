package mx.santander.fiduciarioplus.model.instruction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Table(name = "FID_MX_MAE_LIST_INSTR_ADC")
public class TypeInstructionADC {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LIST_ADICI")
	@Size
	@NotEmpty
	private int idListAdici;
	
	@Column(name = "FK_ID_LIST")
	@Size
	@NotEmpty
	private int fkIdList;
	
	@Column(name = "DSC_NOMBR")
	@Size(min =0, max = 200, message = "Cantidad minima y maxima de caracteres")
	@NotNull
	private String dscNombr;
	
	@Column(name = "DSC_FRMTO")
	@Size(min =0, max = 50, message = "Cantidad minima y maxima de caracteres")
	@NotNull
	private String dscFrmto;
	
	@Column(name = "FLG_LAYOUT")
	@Size(min =0, max = 1, message = "")
	@NotNull
	private int flgLayou;
	
	
	
	public int getIdListAdici() {
		return idListAdici;
	}
	public void setIdListAdici(int idListAdici) {
		this.idListAdici = idListAdici;
	}
	public int getFkIdList() {
		return fkIdList;
	}
	public void setFkIdList(int fkIdList) {
		this.fkIdList = fkIdList;
	}
	public String getDscNombr() {
		return dscNombr;
	}
	public void setDscNombr(String dscNombr) {
		this.dscNombr = dscNombr;
	}
	public String getDscFrmto() {
		return dscFrmto;
	}
	public void setDscFrmto(String dscFrmto) {
		this.dscFrmto = dscFrmto;
	}
	public int getFlgLayou() {
		return flgLayou;
	}
	public void setFlgLayou(int flgLayou) {
		this.flgLayou = flgLayou;
	}
	

}
