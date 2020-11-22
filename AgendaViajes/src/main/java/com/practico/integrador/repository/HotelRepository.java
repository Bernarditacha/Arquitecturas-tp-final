package com.practico.integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practico.integrador.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

	List<Hotel> findByViaje_Id(Long viaje);
	
}
