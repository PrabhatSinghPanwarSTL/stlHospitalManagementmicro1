package com.patient.appointment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.patient.appointment.entities.Patient;
import com.patient.appointment.exceptions.ResourceNotFoundException;
import com.patient.appointment.repositories.PatientRepo;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private PatientRepo patientRepo ;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Patient patient = this.patientRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Patient", "Email : " + username, 0)) ;
		
		return patient;
	}

}
