package com.futbol.demo.core.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.futbol.demo.mapper.UsuariosMapper;
import com.futbol.demo.modelo.Usuarios;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private final UsuariosMapper userMapper;

    @Autowired
    public UserRepositoryImpl(UsuariosMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public List<Usuarios> findAllData() {
        return userMapper.findAll();
    }


	@Override
	public List<Usuarios> insert(Usuarios user) {
		userMapper.insert(user);
		return userMapper.findAll();
	}


	@Override
	public void delete(Usuarios user) {
		userMapper.delete(user);
	}


	@Override
	public Usuarios findUser(Usuarios user) {
		Usuarios localizado = userMapper.findUser(user);
		return localizado;
		
	}
	
	@Override
	public Usuarios findById (int id) {
		Usuarios localizado = userMapper.findById(id);
		return localizado;
	}

}
