package com.futbol.demo.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
@MapperScan("com.futbol.demo.modelo")
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername("sa");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        dataSource.setPassword("");

        // create a table and populate some data
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("Creating tables");
        jdbcTemplate.execute("drop table usuarios if exists");
        jdbcTemplate.execute("create table usuarios(idusuario serial, strlogin varchar(255), strpassword varchar(255), strnombre varchar(255), strapellido1 varchar(255), strapellido2 varchar(255), email varchar(255))");
		jdbcTemplate.update("INSERT INTO usuarios(strlogin, strpassword, strnombre, strapellido1, strapellido2, email) values (?,?,?,?,?,?)", "padepa", "pacopassword", "Paco", "De Lucia", "Pasante", "paco@gmail.com");
		jdbcTemplate.update("INSERT INTO usuarios(strlogin, strpassword, strnombre, strapellido1, strapellido2, email) values (?,?,?,?,?,?)", "midesa", "miguelpassword", "Miguel", "De Cervantes", "Saavedra", "miguel@gmail.com");
		jdbcTemplate.update("INSERT INTO usuarios(strlogin, strpassword, strnombre, strapellido1, strapellido2, email) values (?,?,?,?,?,?)", "manebo", "manolopassword", "Manolo", "Santana", "Chicote", "manolo@gmail.com");
        
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("com.futbol.demo.modelo");
        return sessionFactory;
    }
}
