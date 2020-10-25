package com.practico.integrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practico.integrador.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByUsuarioAndContrasenia(String usuario, String contrasenia);

}