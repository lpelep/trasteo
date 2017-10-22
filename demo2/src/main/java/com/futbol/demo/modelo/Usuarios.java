package com.futbol.demo.modelo;

import java.util.List;

import lombok.Data;

@Data
public class Usuarios {

	private Integer idusuario;
	private String strnombre;
	private String strapellido1;
	private String strapellido2;
	private String strlogin;
	private String strpassword;
	private String email;
	private List<Rol> roles;
	
}
