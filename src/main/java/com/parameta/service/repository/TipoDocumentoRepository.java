package com.parameta.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.parameta.service.dbmodel.Empleado;
import com.parameta.service.dbmodel.TipoDocumento;

public interface TipoDocumentoRepository extends CrudRepository<Empleado, String> {

	@Query(value = ("SELECT u FROM TIPO_DOCUMENTO u WHERE u.codigo = ?1"))
	Optional<TipoDocumento> finByCodigo(String codigo);
}
