package com.gustavo.vendasSpringAngular.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.vendasSpringAngular.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
