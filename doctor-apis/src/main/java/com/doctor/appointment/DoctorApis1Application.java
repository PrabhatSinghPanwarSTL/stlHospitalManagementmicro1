package com.doctor.appointment;

import org.modelmapper.ModelMapper; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.doctor.appointment.config.AppConstants;
//import com.doctor.appointment.entities.Role;
//import com.doctor.appointment.repositories.RoleRepo;


@SpringBootApplication
public class DoctorApis1Application implements CommandLineRunner {

	
	@Autowired
	private PasswordEncoder passwordEncoder ;
	
//	@Autowired
//	private RoleRepo roleRepo ; 
	
	public static void main(String[] args) {
		SpringApplication.run(DoctorApis1Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper() ; 
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(this.passwordEncoder.encode("xyz"));
		
//		try {
//			
//			Role role = new Role() ;
//			role.setId(AppConstants.Role_Doctor);
//			role.setName("Role_Doctor");
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
