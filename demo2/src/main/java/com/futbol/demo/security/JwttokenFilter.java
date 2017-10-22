package com.futbol.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.futbol.demo.core.rol.RolRepository;
import com.futbol.demo.core.user.UserRepository;
import com.futbol.demo.modelo.Usuarios;
import com.futbol.demo.service.JwtService;

public class JwttokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
    private JwtService jwtService;
	
	@Value("${jwt.header}")
	private String header;
	

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		//miramos si existe en la cabecera un token
        String token = request.getHeader(header);
        
        if (token !=null) {
        	jwtService.getClaims(token).ifPresent(id -> {
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                	
                	Usuarios usuario = userRepository.findById(Integer.parseInt(id));
                	if (usuario != null) {
                		
                		usuario.setRoles(rolRepository.findRolByUsuario(usuario));
                		
                		jwtService.guardarUsuarioSesion(usuario, request);
                		
//                		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
//                		
//                		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        
                	}
                }
            });
        }
        
        

        filterChain.doFilter(request, response);
    }
}
