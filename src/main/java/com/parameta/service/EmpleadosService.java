package com.parameta.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmpleadosService {

	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(EmpleadosService.class);
		log.info("Start Empleados Service");
		SpringApplication.run(EmpleadosService.class, args);
	}
}
