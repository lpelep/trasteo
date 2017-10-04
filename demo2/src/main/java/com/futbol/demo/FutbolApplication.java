package com.futbol.demo;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.futbol.demo.modelo.Usuarios;

@MappedTypes(Usuarios.class)
@SpringBootApplication
@MapperScan("com.futbol.demo.mapper")
public class FutbolApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutbolApplication.class, args);
	}
}
