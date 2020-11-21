package com.practico.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practico.integrador.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

}
