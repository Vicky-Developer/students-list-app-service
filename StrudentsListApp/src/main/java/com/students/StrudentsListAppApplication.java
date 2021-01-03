package com.students;

import java.sql.Connection;
import java.sql.DriverManager;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.students.utils.StudentsLogger;

@SpringBootApplication(scanBasePackages = {"com.students"})
@EntityScan(basePackages = "com.students.db.entities")
@EnableJpaRepositories(basePackages = "com.students.db.repository")
public class StrudentsListAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrudentsListAppApplication.class, args);
	}
	
	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/greeting-javaconfig").allowedOrigins(
	 * "http://localhost:3000"); } }; }
	 */
	
	@Bean
	public StudentsLogger logger() {
		return StudentsLogger.getLogger("com.students-list-app.api.v1");
	}
	
	@Bean
	public Connection connection() {
		
		String localurl = "jdbc:postgresql://localhost:5432/Students DB", username = "postgres", password = "admin";

		Connection connection = null;

		try {

			Class.forName( "org.postgresql.Driver" );

			connection = DriverManager.getConnection( localurl, username, password );

		} catch ( Exception e ) {

			System.out.println( "Connection.getConnection() , Exception = " + e );

		}

		return connection;
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	} 

}
