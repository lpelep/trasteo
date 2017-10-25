package com.futbol.demo.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.futbol.demo.core.user.UserRepository;
import com.futbol.demo.modelo.JWTUsuario;
import com.futbol.demo.modelo.Usuarios;
import com.futbol.demo.service.JwtAuthenticationResponse;
import com.futbol.demo.service.JwtService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/rest/usuarios")
public class UsuarioPeticiones {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Value("${jwt.header}")
	private String header;
	
	public UsuarioPeticiones(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RequestMapping(path = "/all", method = GET)
	public @ResponseBody List<Usuarios> getAll(){
		return userRepository.findAllData();
	}
	
	 @RequestMapping(path = "login/", method = POST)
	 public ResponseEntity<?> userLogin(@RequestBody Usuarios usuarios, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
		 
		 //buscamos al usuario en BBDD
	     Usuarios usuario = userRepository.findUser(usuarios);
	     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        
	     //Verificamos que la contraseña enviada coincida con la guardada en BBDD
	     if (encoder.matches(usuarios.getStrpassword(), usuario.getStrpassword())) {
	        	
	        //generamos el token
		    String token = jwtService.toToken(usuario);
		        
		    //hacemos un logout previo si procede
		    if (!jwtService.logout(usuario, request, response)) {
		        	
		    	// si no hemos hecho logout guardamos en sesión.
		        //guardamos el usuario en sesión
			    jwtService.guardarUsuarioSesion(usuario, request);
		    }
		        
		    return ResponseEntity.ok(new JWTUsuario(usuario, token));
		    
	        }else {
	        	return (ResponseEntity<?>) ResponseEntity.ok();
	        }
	    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/insert", method = POST)
	public List<Usuarios> insert(@RequestBody Usuarios usuarios) {
		
		//buscamos al usuario en BBDD
		Usuarios usuario = userRepository.findUser(usuarios);
	    
		if (usuario== null) {
			//no se ha encontrado el usuario, se puede insertar
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			usuarios.setStrpassword(encoder.encode(usuarios.getStrpassword()));
			
		}
		return userRepository.insert(usuarios);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(path = "/delete", method = POST)
	public List<Usuarios> delete(@RequestBody Usuarios usuarios) {
		
		//buscamos al usuario en BBDD
		Usuarios usuario = userRepository.findUser(usuarios);
		
		if (usuario !=null) {
			userRepository.delete(usuario);	
		}
		
		
		return userRepository.findAllData();
	}
	

	 @RequestMapping(path = "/refrescarToken", method = GET)
	 public ResponseEntity<?> refrescaYautentica(HttpServletRequest request) {
		 
		 //cogemos el token de la cabecera
		 String token = request.getHeader(header);
		 String nuevoToken = null;
		 
		 //sacamos el id que está en el token
		 Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
		 
		 //se busca al usuario en BBDD
		 Usuarios usuario = userRepository.findById(idUsuario.intValue());
		 
		 //se comprueba que el token esté caducado
		  if (jwtService.sePuedeRefrescar(token))
			  nuevoToken = jwtService.toToken(usuario);
		  
		  //se devuelve nuevo token
		  return ResponseEntity.ok(new JwtAuthenticationResponse(nuevoToken));
		 
	 }
}
