package com.patient.appointment.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.appointment.entities.Patient;
import com.patient.appointment.payloads.JwtAuthRequest;
import com.patient.appointment.payloads.JwtAuthResponse;
import com.patient.appointment.payloads.PatientDto;
import com.patient.appointment.security.JwtTokenHelper;
import com.patient.appointment.sevices.PatientService;

@RestController
@RequestMapping("/api/v2/auth/")
public class AuthController {

	
	@Autowired
	private JwtTokenHelper jwtTokenHelper ;
	
	@Autowired
	private UserDetailsService userDetailsService ;
	
	@Autowired
	private AuthenticationManager authenticationManager ;
	
	@Autowired
	private PatientService patientService ;
	
	@Autowired
	private ModelMapper modelMapper ;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		
		this.authenticate(request.getUsername(), request.getPassword()) ;
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername()) ;
		
		String token = this.jwtTokenHelper.generateToken(userDetails) ;
		
		
		
		JwtAuthResponse response = new JwtAuthResponse() ;
		response.setToken(token) ;
		response.setUser(this.modelMapper.map((Patient)userDetails, PatientDto.class)) ;
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK) ;
	}
	
	private void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password) ;
		
		

		try {
			
			this.authenticationManager.authenticate(authenticationToken) ;
			
		} catch (BadCredentialsException e) {
			
			System.out.println("Invalid UserDetails !!");
			throw new Exception("Invalide Username or Password") ;
		}
		
	}
	
	
	
	//register new user API
	@PostMapping("/registerpatient")
	public ResponseEntity<PatientDto> registerUser(@RequestBody PatientDto patientDto) {
		
		PatientDto registeredPatient = this.patientService.registerNewPatient(patientDto) ;
		
		
		return new ResponseEntity<PatientDto>(registeredPatient, HttpStatus.CREATED) ;
	}
	
	
	
}
