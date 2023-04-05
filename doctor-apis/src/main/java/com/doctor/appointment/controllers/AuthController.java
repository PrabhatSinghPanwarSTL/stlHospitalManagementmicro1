package com.doctor.appointment.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.appointment.entities.Doctor;
import com.doctor.appointment.payloads.DoctorDto;
import com.doctor.appointment.payloads.JwtAuthRequest;
import com.doctor.appointment.payloads.JwtAuthResponse;
import com.doctor.appointment.security.JwtTokenHelper;
import com.doctor.appointment.services.DoctorService;




@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired 
	private JwtTokenHelper jwtTokenHelper ;
	
	@Autowired
	private UserDetailsService userDetailsService ;
	
	@Autowired
	private AuthenticationManager authenticationManager ;
	
	@Autowired
	private DoctorService doctorService ;
	
	@Autowired
	private ModelMapper modelMapper ;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		
		this.authenticate(request.getUsername(), request.getPassword()) ;
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername()) ;
		
		String token = this.jwtTokenHelper.generateToken(userDetails) ;
		
		
		
		
		JwtAuthResponse response = new JwtAuthResponse() ;
		response.setToken(token) ;
		response.setUser(this.modelMapper.map((Doctor)userDetails, DoctorDto.class)) ;
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK) ;
		
	}


	private void authenticate(String username, String password) throws Exception {
		
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password) ;
		

		try {
			this.authenticationManager.authenticate(authenticationToken) ;
		}
		catch(BadCredentialsException e) {
			
			System.out.println("Invalid Details !!");
			throw new Exception("Invalid Username or Password") ;
			
		}
		
	}
	
	
	//register new user
	@PostMapping("/register")
	public ResponseEntity<DoctorDto> registerDoctor(@RequestBody DoctorDto doctorDto) {
		
		DoctorDto registeredDoctor = this.doctorService.registerNewDoctor(doctorDto) ;
		
		return new ResponseEntity<DoctorDto>(registeredDoctor, HttpStatus.CREATED) ;
		
	}
	
}