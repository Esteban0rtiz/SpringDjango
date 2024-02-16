package com.example.demo.repositorios;

import com.example.demo.entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
}
