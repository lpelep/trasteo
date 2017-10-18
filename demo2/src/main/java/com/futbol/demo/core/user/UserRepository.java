package com.futbol.demo.core.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.futbol.demo.modelo.Usuarios;



@Repository
public interface UserRepository {

    List<Usuarios> findAllData();

    List<Usuarios> insert(Usuarios user);

    List<Usuarios> delete(Usuarios user);

    Usuarios findUser(Usuarios user);
    
    Usuarios findById (int id);
}