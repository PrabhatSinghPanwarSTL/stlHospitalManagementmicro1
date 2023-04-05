package com.appointments.pado;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class AppointmentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentMicroserviceApplication.class, args);
	}

	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper() ; 
	}
	
}
