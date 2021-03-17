package com.parameta.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.parameta.service.dbmodel.Empleado;

public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {

	@Query(value = ("SELECT u FROM EMPLEADO u WHERE u.tipoDocumento.codigo = ?1 AND u.nroDocumento = ?2"))
	Optional<Empleado> finByTpNroDoc(String tpDoc, String nroDoc);
}
