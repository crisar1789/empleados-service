package com.parameta.service.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "TIPO_DOCUMENTO")
public class TipoDocumento {

	@Id
	@Column(name = "CODIGO")
	private String codigo;
	@Column(name = "NOMBRE")
	private String nombre;
	
	public TipoDocumento() {
		super();
	}
}
