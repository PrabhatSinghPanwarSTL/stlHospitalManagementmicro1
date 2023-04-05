package com.doctor.appointment.payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token ;
	
	private DoctorDto user ;
	
}
