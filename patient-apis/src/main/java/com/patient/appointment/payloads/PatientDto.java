package com.patient.appointment.payloads;

import java.util.ArrayList;
import java.util.List;

import com.patient.appointment.entities.Apointment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PatientDto {

	private int id ;
	
	@NotBlank(message = "can not leave Blank !!")
	@Size(min=5, message = "Patient Name must be minimum of 5 characters !!")
	private String name ;
	
	@Email(message = "Entered Email is not appropriate !!")
	private String email ;
	
	@NotBlank(message = "can not leave Blank !!")
	@Size(min=5, message = "Password must be minimum of 5 characters !!")
	private String password ;
	
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Enter valid Mobile Number !!")
	private String mobileno ;
	
	
	private List<Apointment> apointments = new ArrayList<>() ;
	
}
