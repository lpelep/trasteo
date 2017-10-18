package com.futbol.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.futbol.demo.core.user.UserRepository;
import com.futbol.demo.modelo.Usuarios;
import com.futbol.demo.service.JwtAuthenticationResponse;
import com.futbol.demo.service.JwtService;


@RestController
@RequestMapping("/rest/usuarios")
public class UsuarioPeticiones {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	public UsuarioPeticiones(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RequestMapping("/all")
	public @ResponseBody List<Usuarios> getAll(){
		return userRepository.findAllData();
	}
	
	 @RequestMapping(path = "login/", method = RequestMethod.POST)
	    public ResponseEntity<?> userLogin(@RequestBody Usuarios usuarios, BindingResult bindingResult) {
		 
	        Usuarios usuario = userRepository.findUser(usuarios);
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        if (encoder.matches(usuarios.getStrpassword(), usuario.getStrpassword())) {
		        
		        String token = jwtService.toToken(usuario);
		        
		        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	        }else {
	        	return (ResponseEntity<?>) ResponseEntity.ok();
	        }
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
