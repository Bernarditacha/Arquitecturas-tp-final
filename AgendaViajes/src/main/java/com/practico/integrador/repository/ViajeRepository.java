package com.practico.integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practico.integrador.model.Viaje;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

	@Query("SELECT v FROM Viaje v")
	List<Viaje> getRealizados();
	
}
