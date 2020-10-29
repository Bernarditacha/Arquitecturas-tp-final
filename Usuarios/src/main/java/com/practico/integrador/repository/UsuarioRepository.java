package com.practico.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practico.integrador.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsuarioAndContrasenia(String usuario, String contrasenia);

}