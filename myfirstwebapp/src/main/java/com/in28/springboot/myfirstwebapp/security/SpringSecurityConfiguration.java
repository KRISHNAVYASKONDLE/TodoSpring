package com.in28.springboot.myfirstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SpringSecurityConfiguration {

//	InMemoryUserDetailsManager

	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {

		
		
	  UserDetails user1=createNewUser("krishna", "krishna");
	  UserDetails user2=createNewUser("chandra", "chandra");
	  
	  return new InMemoryUserDetailsManager(user1,user2); 
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> encoder = input -> passcoder().encode(input);

		UserDetails userdetails = User.builder().passwordEncoder(encoder).username(username).password(password)
				.roles("USER", "ADMIN").build();

		return userdetails;
	}

	@Bean
	public PasswordEncoder passcoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests(
		 auth-> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		return http.build();
	}
	

}
