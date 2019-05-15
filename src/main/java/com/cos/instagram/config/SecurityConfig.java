package com.cos.instagram.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService; 
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	//허용해 주는 부분
		http.csrf().disable();	//postman으로 테스트하는 것 자체도 csrf공격
//		http.authorizeRequests().antMatchers("/image/**").authenticated()
//		.anyRequest().permitAll().and().formLogin().permitAll();	//.and()로 추가 할 수 있다.
		http.authorizeRequests().antMatchers("/").authenticated().anyRequest().permitAll()
		.and().formLogin().loginPage("/user/login").loginProcessingUrl("/user/loginProc");
	}
	
	
}
