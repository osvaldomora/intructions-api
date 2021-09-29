package mx.santander.fiduciarioplus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "registro_documento")
public class RegistroDocumentoModel {

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGISTRO_DOCUMENTO_SEQ")
    //@SequenceGenerator(sequenceName = "SEQREGISTRO_DOCUMENTO", allocationSize = 1, name = "REGISTRO_DOCUMENTO_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUMERO_UNICO_DOCUMENTO")
	private Long numeroUnicoDoc;
	
	@Column(name = "CLAVE_TIPO_DOCUMENTO")
	private String claveTipoDoc;
	
	@Column(name = "NUMERO_DOCUMENTO")
	private String numeroDoc;
	
	@Column(name = "CONCEPTO_DOCUMENTO")
	private String conceptoDoc;
	
	@Column(name = "PARTE_CONTRATO")
	private String parteContrato;	//FIDEICOMITENTE|FIDEICOMISARIO
	
	@Column(name = "CLAVE_PO")
	private Long clavePo;
	
	@Column(name = "TESTIMONIO_CERTIFICADA_SIMPLE")
	private String testimonioCertifSimple;	//T|S
	
	@Column(name = "FECHA_VENCIMIENTO_DOCUMENTO")
	private Date fechaVencimientoDoc;
	
	@Column(name = "FECHA_RECEPCION")
	private Date fechaRecepcion;
	
	@Column(name = "FECHA_EXPEDICION")
	private Date fechaExpedicion;
	
	@Column(name = "FECHA_ENTRADA_ARCHIVO")
	private Date fechaEntradaArchivo;
	
	@Column(name = "INDIVIDUAL_MASIVO")
	private String individualMasivo;
	
	@Column(name = "CIFRA_CONTROL")
	private Long cifraControl;
	
	@Column(name = "ESTATUS_APLICACION_DOCUMENTO")
	private String estatusAplicacionDoc;
	
	@Column(name = "ESTATUS_DOCUMENTO")
	private String estatusDoc;
	
	@Column(name = "CLAVE_INMUEBLE")
	private String claveInmueble;
	
	@Column(name = "COPIA_ORIGINAL")
	private String copiaOriginal;
	
	@Column(name = "FLG_DETNT")
	private String flgDetnt;
	
	@Column(name = "ID_DOC_DETNT")
	private String idDocDetnt;
	
	@Column(name = "VAL_EST_INTEG")
	private String valEstInteg;
	
	@Column(name = "FLG_REQ_TRAZA")
	private String flgReqTraza;
	
	@Column(name = "FLG_REQ_OPER")
	private String flgReqOper;
	
	
}
