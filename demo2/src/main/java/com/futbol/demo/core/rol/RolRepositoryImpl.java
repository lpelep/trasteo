package com.futbol.demo.core.rol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.futbol.demo.mapper.RolMapper;
import com.futbol.demo.modelo.Rol;
import com.futbol.demo.modelo.Usuarios;

@Repository
public class RolRepositoryImpl implements RolRepository {
	
	private final RolMapper rolmapper;

	@Autowired
	public RolRepositoryImpl(RolMapper rolmapper) {
		this.rolmapper = rolmapper;
	}
	
	@Override
	public List<Rol> findRolByUsuario(Usuarios usuario) {
		
		List<Rol> roles = rolmapper.findRolByUsuario(usuario); 
		return roles;
	}

}
