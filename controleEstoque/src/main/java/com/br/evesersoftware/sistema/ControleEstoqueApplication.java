package com.br.evesersoftware.sistema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan(basePackages = {"com.br.evesersoftware.model"})
@ComponentScan(basePackages = {"com.*"}) 
@EnableJpaRepositories(basePackages = {"com.br.evesersoftware.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration

public class ControleEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleEstoqueApplication.class, args);
		/*System.out.println(new BCryptPasswordEncoder().encode("123"));*/
	}
	
	/*Mapeamento Global que refletem em todo o sistema*/
	
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/usuario/**")
		.allowedMethods("*")
		.allowedOrigins("*");
		/*Liberando o mapeamento de usuario para todas as origens*/
		
	}
}
