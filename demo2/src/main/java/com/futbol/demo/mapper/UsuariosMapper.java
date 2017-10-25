package com.futbol.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.futbol.demo.modelo.Usuarios;

@Component
@Mapper
public interface UsuariosMapper {

	List<Usuarios> findAll();

	void insert(Usuarios usuario);

	void delete(Usuarios usuario);
	
	Usuarios findUser(Usuarios usuario);

	Usuarios findById(int id);
}
