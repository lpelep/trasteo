package com.futbol.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.futbol.demo.modelo.Rol;
import com.futbol.demo.modelo.Usuarios;

@Component
@Mapper
public interface RolMapper {

	List<Rol> findRolByUsuario(Usuarios usuario);
}
