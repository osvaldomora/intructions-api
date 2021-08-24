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
@Table(name = "FID_MX_MAE_LIST_INSTR")
public class TypeInstruction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LIST")
	@Size
	@NotEmpty
	private int idList;
	
	@Column(name = "DSC_TPO_INSTR")
	@Size(min =0, max = 200, message = "Cantidad minima y maxima de caracteres")
	@NotNull
	private String dscTpoInstr;
	
	@Column(name = "DSC_NOMBR")
	@Size(min =0, max = 200, message = "Cantidad minima y maxima de caracteres")
	@NotNull
	private String dscNombr;
	
	@Column(name = "COD_DOC")
	@Size(min =0, max = 4, message = "")
	@NotNull
	private int codDoc;
	
	@Column(name = "DSC_FRMTO")
	@Size(min =0, max = 10, message = "Cantidad minima y maxima de caracteres")
	@NotNull
	private String dscFrmto;
	
	@Column(name = "FLG_LAYOU")
	@Size(min =0, max = 1, message = "")
	@NotNull
	private int flgLayou;
	
	@Column(name = "VAL_ADICI")
	@Size(min =0, max = 1, message = "")
	@NotNull
	private int valAdici;
	
	@Column(name = "VAL_INSTR")
	@Size
	@NotNull
	private String valInstr;

	public int getIdList() {
		return idList;
	}

	public void setIdList(int idList) {
		this.idList = idList;
	}

	public String getDscTpoInstr() {
		return dscTpoInstr;
	}

	public void setDscTpoInstr(String dscTpoInstr) {
		this.dscTpoInstr = dscTpoInstr;
	}

	public String getDscNombr() {
		return dscNombr;
	}

	public void setDscNombr(String dscNombr) {
		this.dscNombr = dscNombr;
	}

	public int getCodDoc() {
		return codDoc;
	}

	public void setCodDoc(int codDoc) {
		this.codDoc = codDoc;
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

	public int getValAdici() {
		return valAdici;
	}

	public void setValAdici(int valAdici) {
		this.valAdici = valAdici;
	}

	public String getValInstr() {
		return valInstr;
	}

	public void setValInstr(String valInstr) {
		this.valInstr = valInstr;
	}
	

}
