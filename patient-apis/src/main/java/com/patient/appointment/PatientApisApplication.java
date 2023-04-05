package com.patient.appointment;

import org.modelmapper.ModelMapper; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.patient.appointment.config.AppConstants;
//import com.patient.appointment.entities.Role;
//import com.patient.appointment.repositories.RoleRepo;

@SpringBootApplication
public class PatientApisApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder ;
	
//	@Autowired
//	private RoleRepo roleRepo ;
	
	
	public static void main(String[] args) {
		SpringApplication.run(PatientApisApplication.class, args);
	}

	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper() ; 
	}
	
	@Bean
	public RestTemplate restTemplate() {
		
		return new RestTemplate() ;
		
	}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(this.passwordEncoder.encode("xyz"));
		
//		try {
//			
//			Role role = new Role() ;
//			role.setId(AppConstants.Patient_Role);
//			role.setName("Patient_Role");
//			
//			Role roles = role ;
//			
//			Role result = this.roleRepo.save(roles) ;
//			
//			System.out.println(result.getName());
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
	}
	
	
	
}
