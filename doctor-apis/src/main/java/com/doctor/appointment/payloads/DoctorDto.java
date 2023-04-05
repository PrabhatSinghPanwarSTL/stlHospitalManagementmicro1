package com.doctor.appointment.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
public class DoctorDto {
	
	
	private int Did ;
	
	@NotEmpty
	@Size(min = 5, message = "The name should of minimum size 5 !!!")
	private String Dname ;
	
	@Email(message = "Enter Proper Email !!")
	private String email ;
	
	@NotEmpty
	@Size(min = 3, message = "password size should be minimum 3 !!!")
	private String Dpassword ;
	
	@NotEmpty
	@Pattern(regexp="(^$|[0-9]{10})", message = "Enter a valid phone Number !!!")
	private String Dmobileno ;

	@NotEmpty(message = "Must put a speciality !!!")
	private String Dspeciality ;
	
	
	@JsonIgnore
	public String getDpassword() {
		return this.Dpassword ;
	}
	
	@JsonProperty
	public void setDpassword(String password) {
		this.Dpassword = password ;
	}
	
}
