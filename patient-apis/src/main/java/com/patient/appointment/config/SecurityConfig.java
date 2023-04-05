package com.patient.appointment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.patient.appointment.security.CustomUserDetailsService;
import com.patient.appointment.security.JwtAuthenticationEntryPoint;
import com.patient.appointment.security.JwtAuthenticationFilter;


//import org.springframework.security.web.util.matcher.AntPathRequestMatcher ;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Autowired
	CustomUserDetailsService customUserDetailsService ;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint ;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter ;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/api/v2/auth/**").permitAll()
		.requestMatchers(HttpMethod.GET).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS) ;
				
		http.authenticationProvider(daoAuthenticationProvider()) ;
		
		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) ;
		
		DefaultSecurityFilterChain defaultSecurityFilterChain = http.build() ;
		return defaultSecurityFilterChain ;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
	    return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider() ;
		provider.setUserDetailsService(this.customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider ;
		
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager() ;
	}

	
	@Bean
	public FilterRegistrationBean coresFilter() {
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource() ;
		
		CorsConfiguration corsConfiguration = new CorsConfiguration() ;
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOriginPattern("*") ;
		corsConfiguration.addAllowedHeader("Authorization");
		corsConfiguration.addAllowedHeader("Content-Type");
		corsConfiguration.addAllowedHeader("Accept");
		corsConfiguration.addAllowedMethod("POST");
		corsConfiguration.addAllowedMethod("GET");
		corsConfiguration.addAllowedMethod("DELETE");
		corsConfiguration.addAllowedMethod("PUT");
		corsConfiguration.addAllowedMethod("OPTIONS");
		corsConfiguration.setMaxAge(3600L);
		
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		FilterRegistrationBean bean =  new FilterRegistrationBean(new CorsFilter(source)) ;
		
		bean.setOrder(-110) ;
	
		return bean ;
		
	}
	
}
