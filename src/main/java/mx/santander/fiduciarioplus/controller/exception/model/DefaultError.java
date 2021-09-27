package mx.santander.fiduciarioplus.controller.exception.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author David Gonzalez - (Arquetipo creado por Santander Tecnologia Mexico)
 * 
 * La clase ErrorBean, usada para indicar los errores que sucedieron durante la ejecucion.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultError implements Serializable {

    /** Variable para serializar la clase. */
    private static final long serialVersionUID = 1L;

	/** La variable code. */
    private String code;
    
    /** La variable message. */
    private String message;

    /** La variable level. */
    private String level;

    /** La variable description. */
    private String description;
    
    /** La variable more info. */
    private String moreInfo;

    /**
     * Constructor que recibe todos los parametros del enumerador de mensajes
     * 
     * @param code Un codigo de error unico, el cual pueda ser identificado y localizado para mas detalles. Debe ser human readable, por tanto no deberia ser un codigo numerico, sino alfanumerico.
     * @param message Un mensaje de error claro: Por consideraciones de seguridad estos mensajes de error no deben contener informacion interna que pudiera implicar un riesgo a la seguridad e integridad.
     * @param level  Un nivel de error: info, warning, error.
     * @param description Una descripcion detallada.
     * @param moreInfo Un link a la documentacion del codigo de error.
     */
    public DefaultError(String code, String message, String level, 
    		String description, String moreInfo) {
		this.code = code;
		this.message = message;
		this.level = level;
		this.description = description;
		this.moreInfo = moreInfo;
	}

    
    /**
     * Constructor que recibe todos los parametros del enumerador de mensajes, excepto una descripcion detallada
     * 
     * @param code Un codigo de error unico, el cual pueda ser identificado y localizado para mas detalles. Debe ser human readable, por tanto no deberia ser un codigo numerico, sino alfanumerico.
     * @param message Un mensaje de error claro: Por consideraciones de seguridad estos mensajes de error no deben contener informacion interna que pudiera implicar un riesgo a la seguridad e integridad.
     * @param level  Un nivel de error: info, warning, error.
     * @param moreInfo Un link a la documentacion del codigo de error.
     */
    public DefaultError(String code, String message, String level, String moreInfo) {
		this.code = code;
		this.message = message;
		this.level = level;
		this.description = null;
		this.moreInfo = moreInfo;
	}

    /**
     * Obtiene el valor de la variable code.
     *
     * @return el code
     */
    public String getCode() {
        return code;
    }

    /**
     * Obtiene el valor de la variable message.
     *
     * @return el message
     */
    public String getMessage() {
        return message;
    }


    /**
     * Obtiene el valor de la variable nivel.
     *
     * @return el level
     */
    public String getLevel() {
        return level;
    }


    /**
     * Obtiene el valor de la variable description.
     *
     * @return el description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtiene el valor de la variable more info.
     *
     * @return el more info
     */
    public String getMoreInfo() {
        return moreInfo;
    }


}