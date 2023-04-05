package com.doctor.appointment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource ;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

import com.doctor.appointment.security.CustomUserDetailService;
import com.doctor.appointment.security.JwtAuthenticationEntryPoint;
import com.doctor.appointment.security.JwtAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUserDetailService ;
	
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint ;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter ;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http.
			csrf().disable() 
			.authorizeHttpRequests()
			.antMatchers("/api/v1/auth/**").permitAll()
			.antMatchers(HttpMethod.GET).permitAll() 
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
			.and() 
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) ;
		
		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) ;
			
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub

		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder()) ;
		
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder() ;
	}

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
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
	
		return bean ;
	}
	
	
}
