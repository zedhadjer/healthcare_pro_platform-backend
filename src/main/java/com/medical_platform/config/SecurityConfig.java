package com.medical_platform.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity //annotation activate the spring security for web app
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
		// the chain of security filters
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
		//(Cross-Origin Resource Sharing)
			.csrf(crsf -> crsf.disable())
			//Configure the protection CSRF (Cross-Site Request Forgery)
			// disabled csrf ==> use JWT tokens
			.authorizeHttpRequests(auth -> auth.requestMatchers("api/**").permitAll()
					.anyRequest().authenticated());
		/*
		 * .antMatchers() - Defines a URL pattern to match
		 * 		"/api/**" - Ant Pattern:
		 * /api/ - Starts with /api/
		* - Anything after (0 or more segments)
		*		.permitAll() - Allows everyone (authenticated or not)*/
		/**
		 * on production mode
		 * .antMatchers("/api/public/**").permitAll()  // Routes publiques
		 * .antMatchers("/api/admin/**").hasRole("ADMIN")  // Seulement admins
		 * .antMatchers("/api/**").authenticated()  // Utilisateurs connectés
		 */
		/*.anyRequest() - All other requests (that do not match the previous rules)
		 * .authenticated() - Require authentication*/
		return http.build();
		//Constructs and returns the configured SecurityFilterChain object.
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration(); 
		
				//Authorized origins
				configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
				//Authorized HTTP methods
				configuration.setAllowedMethods(Arrays.asList("GET" , "POST" , "PUT" , "DELETE"));
				// Authorized headers
				configuration.setAllowedHeaders(Arrays.asList("*"));
				// Authorized credentials
				configuration.setAllowCredentials(true);
				 UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
				 //a Spring class that associates URL patterns with CORS configurations
				 source.registerCorsConfiguration("/**", configuration);
				 //Method that saves a CORS configuration for a URL pattern
				 
				return (CorsConfigurationSource) source;
		
	}
}
