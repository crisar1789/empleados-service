package com.parameta.service.endpoint;

import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.parameta.service.dbmodel.Empleado;
import com.parameta.service.dbmodel.TipoDocumento;
import com.parameta.service.empleados.EmpleadoRequest;
import com.parameta.service.empleados.EmpleadoResponse;
import com.parameta.service.empleados.SrvResponse;
import com.parameta.service.repository.EmpleadoRepository;
import com.parameta.service.repository.TipoDocumentoRepository;

@Endpoint
public class EmpleadoEndpoint {

	private static final String NAMESPACE_URI = "http://www.parameta.com/service/empleados";
	Logger log = LoggerFactory.getLogger(EmpleadoEndpoint.class);
	private EmpleadoRepository empleadoRepository;
	private TipoDocumentoRepository tpDocRepository;
	
	@Autowired
    public EmpleadoEndpoint(EmpleadoRepository empleadoRepository, TipoDocumentoRepository tpDocRepository) {
        this.empleadoRepository = empleadoRepository;
        this.tpDocRepository = tpDocRepository;
    }
	
	/**
	 * Método encargado de realizar el proceso para guardar un empleado
	 * @param request Objeto con los datos del empleado
	 * @return
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "EmpleadoRequest")
    @ResponsePayload
    public EmpleadoResponse guardarEmpleado(@RequestPayload EmpleadoRequest request) {
		SrvResponse srvResponse = new SrvResponse();
		try {
			// Se busca el tipo de documento
			Optional<TipoDocumento> optionltipoDoc = tpDocRepository.finByCodigo(request.getTipoDocumento());
			
			// Se consulta el empleado por tipo y número de documento
			Optional<Empleado> optionltipoEmpleado = empleadoRepository.finByTpNroDoc(request.getTipoDocumento(), request.getNroDocumento());
			
			// Se verifica si se encontró un registro
			if (!optionltipoEmpleado.isPresent()) {
				Empleado empleado = new Empleado(request.getNombres(), request.getApellidos(), 
						optionltipoDoc.get(), request.getNroDocumento(), Timestamp.valueOf(request.getFechaNacimiento() + " 00:00:00"),
						Timestamp.valueOf(request.getFechaVinculacion() + " 00:00:00"), request.getCargo(), Integer.valueOf(request.getSalario()));
						
				// Se registra el empleado
				empleadoRepository.save(empleado);
				
				srvResponse.setCodigo("200");
				srvResponse.setDescripcion("El empleado fue resgistrado exitosamente");
			} else {
				srvResponse.setCodigo("400");
				srvResponse.setDescripcion("El tipo y número de documento ya se encunentran registrados para otro empleado");
			}
		} catch(NoSuchElementException e) {
			log.error("Business error", e);
			srvResponse.setCodigo("400");
			srvResponse.setDescripcion("El tipo de documento no existe");
		} catch(IllegalArgumentException e) {
			log.error("Business error", e);
			srvResponse.setCodigo("400");
			srvResponse.setDescripcion("El formato de fecha no corresponde al esperado. Se espera yyyy-MM-dd");
		} catch(Exception e) {
			log.error("Business error", e);
			srvResponse.setCodigo("500");
			srvResponse.setDescripcion("Ocurrio un error durante el registro: " + e.getMessage());
		}
 
		EmpleadoResponse response = new EmpleadoResponse();
		response.setContext(srvResponse);
        return response;
    }
}
