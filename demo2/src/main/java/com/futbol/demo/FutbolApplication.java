package com.futbol.demo;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.futbol.demo.modelo.Usuarios;

@MappedTypes(Usuarios.class)
@SpringBootApplication
@MapperScan("com.futbol.demo.mapper")
public class FutbolApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutbolApplication.class, args);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("pacopassword es: " + encoder.encode("pacopassword"));
		System.out.println("miguelpassword es: " + encoder.encode("miguelpassword"));
		System.out.println("manolopassword es: " + encoder.encode("manolopassword"));
	}
	
	@Bean
	HealthIndicator probandoSalud() {
		return new HealthIndicator() {
			
			@Override
			public Health health() {
				return Health.status("estado Bean: ")
				.withDetail("timestamp", System.currentTimeMillis())
				.build();
			}
		};
	}
}
