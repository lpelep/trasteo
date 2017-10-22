package com.futbol.demo.modelo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class JWTUsuario implements Serializable {

	private static final long serialVersionUID = 877413867751156138L;
	
	private Integer idusuario;
	private String strnombre;
	private String strapellido1;
	private String strapellido2;
	private String strlogin;
	private List<Rol> roles;
	private String token;
	
	public JWTUsuario (Usuarios usuario, String token) {
		this.idusuario = usuario.getIdusuario();
		this.strnombre = usuario.getStrnombre();
		this.strapellido1 = usuario.getStrapellido1();
		this.strapellido2 = usuario.getStrapellido2();
		this.strlogin = usuario.getStrlogin();
		this.roles = usuario.getRoles();
		this.token = token;
	}
}
