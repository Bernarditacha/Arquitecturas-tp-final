package com.practico.integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practico.integrador.model.Viaje;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

	@Query("select v from Viaje v where v.fechaFin < now() and v.usuario = :usuario")
	List<Viaje> getRealizados(Long usuario);
	
	@Query("select v from Viaje v where v.fechaFin > now() and v.usuario = :usuario")
	List<Viaje> getPendientes(Long usuario);
	
	List<Viaje> findByUsuario(Long usuario);
	
}
