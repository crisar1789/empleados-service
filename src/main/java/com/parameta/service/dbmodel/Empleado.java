package com.parameta.service.dbmodel;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "EMPLEADO")
public class Empleado {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name = "NOMBRES")
	private String nombres;
	@Column(name = "APELLIDOS")
	private String apellidos;
	@ManyToOne
	@JoinColumn(name = "TIPO_DOCUMENTO")
	private TipoDocumento tipoDocumento;
	@Column(name = "NUMERO_DOCUMENTO")
	private String nroDocumento;
	@Column(name = "FECHA_NACIMIENTO")
	private Timestamp fechaNacimeinto;
	@Column(name = "FECHA_VINCULACION")
	private Timestamp fechaVinculacion;
	@Column(name = "CARGO")
	private String cargo;
	@Column(name = "SALARIO")
	private Integer salario;
	
	public Empleado() {
		super();
	}

	public Empleado(String nombres, String apellidos, TipoDocumento tipoDocumento, String nroDocumento,
			Timestamp fechaNacimeinto, Timestamp fechaVinculacion, String cargo, Integer salario) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.fechaNacimeinto = fechaNacimeinto;
		this.fechaVinculacion = fechaVinculacion;
		this.cargo = cargo;
		this.salario = salario;
	}
}
