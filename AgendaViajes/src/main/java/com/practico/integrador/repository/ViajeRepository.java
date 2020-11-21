package com.practico.integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practico.integrador.model.Viaje;
import com.practico.integrador.utils.ReporteZonasVisitadas;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

	@Query("select v from Viaje v where v.fechaFin < now() and v.usuario = :usuario")
	List<Viaje> findByRealizados(Long usuario);
	
	@Query("select v from Viaje v where v.fechaFin > now() and v.usuario = :usuario")
	List<Viaje> findByPendientes(Long usuario);
	
	List<Viaje> findByUsuario(Long usuario);
	
	@Query("select v.ciudadDestino as zona, count(*) as viajes from Viaje v group by v.ciudadDestino order by count(*) desc")
	List<ReporteZonasVisitadas> findByZonasGeograficasMasVisitadas();
	
}
