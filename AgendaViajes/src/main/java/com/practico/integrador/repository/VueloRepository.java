package com.practico.integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practico.integrador.model.Vuelo;

public interface VueloRepository extends JpaRepository<Vuelo, Long>{

	List<Vuelo> findByViaje_Id(Long viaje);

}
