package com.doctor.appointment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doctor.appointment.entities.Doctor;
import com.doctor.appointment.exceptions.ResourceNotFoundException;
import com.doctor.appointment.repositories.DoctorRepo;



@Service
public class CustomUserDetailService implements UserDetailsService {

	
	@Autowired
	private DoctorRepo doctorRepo ;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		// Loading User From DataBase by username
		Doctor doctor = this.doctorRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "Demail : " + username, 0)) ;
		
		
		return doctor;
	}

}
