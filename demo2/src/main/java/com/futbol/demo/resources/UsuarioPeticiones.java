package com.futbol.demo.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.futbol.demo.core.user.UserRepository;
import com.futbol.demo.modelo.Usuarios;


@RestController
@RequestMapping("/rest/usuarios")
public class UsuarioPeticiones {

	private UserRepository userRepository;
	
	public UsuarioPeticiones(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RequestMapping("/all")
	public @ResponseBody List<Usuarios> getAll(){
		return userRepository.findAllData();
	}
	
	@GetMapping("/insert")
	public List<Usuarios> insert(){
		
		Usuarios usuario = new Usuarios();
		usuario.setStrnombre("Juan");
		usuario.setStrapellido1("Juan Password");
		userRepository.insert(usuario);
		
		return userRepository.findAllData();
	}
	
	@GetMapping("/delete")
	public List<Usuarios> delete(){
		Usuarios usuario = new Usuarios();
		usuario.setStrnombre("Juan");
		userRepository.delete(usuario);
		
		return userRepository.findAllData();
	}
	
}
