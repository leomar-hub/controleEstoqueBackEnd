package com.br.evesersoftware.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.br.evesersoftware.service.ImplementacaoUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		
		/*Ativando a permissãp para acesso a página inicial do sistema*/
		.disable().authorizeRequests().antMatchers("/").permitAll().antMatchers("/index").permitAll()
		
		/*URL de Logout - Redireciona após o user deslogar do sistema*/
		.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
		
		/*Mapeia a URL de logout e invalida o usuário*/
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		/*Filtra requisições de login para autentication*/
		.and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
									UsernamePasswordAuthenticationFilter.class)
		
		/*Filtra demais requisições para verificar a presenção do TOKEN JWT no HEADER HTTP*/
		.addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*Service que irá consultar o usuário no bando de dados*/
		auth.userDetailsService(implementacaoUserDetailsService)
		
		/*Padrão de codificação de senha*/
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
