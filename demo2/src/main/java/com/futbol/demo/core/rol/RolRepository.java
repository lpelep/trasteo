package com.futbol.demo.core.rol;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.futbol.demo.modelo.Rol;
import com.futbol.demo.modelo.Usuarios;

@Repository
public interface RolRepository {

	List<Rol> findRolByUsuario(Usuarios usuario);
}
