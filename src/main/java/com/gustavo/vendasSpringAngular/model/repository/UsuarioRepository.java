package com.gustavo.vendasSpringAngular.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.vendasSpringAngular.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
